package state;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.Timer;

import sun.misc.JavaAWTAccess;

import chess.Cell;
import chess.Main;
import chess.Player;

public class StateLogger implements Serializable{

	private static final long serialVersionUID = 1L;

	private static StateLogger instance = null;
	
	ArrayList<MoveSequence> moveHistory;
	Player whitePlayer;
	Player blackPlayer;
	
	public Player getWhitePlayer() {
		return whitePlayer;
	}

	public void setWhitePlayer(Player whitePlayer) {
		this.whitePlayer = whitePlayer;
	}

	public Player getBlackPlayer() {
		return blackPlayer;
	}

	public void setBlackPlayer(Player blackPlayer) {
		this.blackPlayer = blackPlayer;
	}

	public StateLogger(){
		moveHistory = new ArrayList<MoveSequence>();
	}
	
	public final List<MoveSequence> getMoveHistory(){
		return Collections.unmodifiableList(this.moveHistory);
	}
	
	public static StateLogger getInstance(){
		if(StateLogger.instance == null)
			StateLogger.instance = new StateLogger();
		return StateLogger.instance;
	}
	
	public void addNewMove(Cell previous, Cell next){
		moveHistory.add(new MoveSequence(previous, next));;
		
	}
	
	public void switchPlayerWithoutTurn(){
		moveHistory.add(new MoveSequence());
	}
	
	public void save(int slot) throws IOException{
		FileOutputStream outputFile = new FileOutputStream("savegame_" + slot + ".ser");
		ObjectOutputStream outputObject = new ObjectOutputStream(outputFile);
		outputObject.writeObject(this);
		outputObject.close();
		outputFile.close();
	}
	
	public void load(int slot) throws IOException, ClassNotFoundException, InterruptedException{
		FileInputStream fileIn = new FileInputStream("savegame_" + slot + ".ser");
        ObjectInputStream in = new ObjectInputStream(fileIn);
        StateLogger savedState = (StateLogger) in.readObject();
        in.close();
        fileIn.close();
        
        
		Main.restartGame();
        
		Main game = Main.getGame();
		game.updateUIForChessGame();
		game.setWhitePlayer(savedState.getWhitePlayer());
		game.setBlackPlayer(savedState.getBlackPlayer());
		
		
		game.getBoard().setEnabled(false);
		
		processMoves(0, savedState.moveHistory, game);

	}
	
	private void processMoves(final int currentIndex, final ArrayList<MoveSequence> moves, final Main game){
		
		if(currentIndex >= moves.size())
		{
			game.getBoard().setEnabled(true);
			return;
		}
		
		MoveSequence ms = moves.get(currentIndex);
    	if(ms.isTurnWithoutMove())
    	{
    		game.changeMoveWithoutTurn();
    		return;
    	}
    	
    	game.doMove(ms.getPreviousX(), ms.getPreviousY());
    	game.doMove(ms.getNextX(), ms.getNextY());
    	
    	ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                processMoves(currentIndex + 1, moves, game);
            }
        };
        Timer timer = new Timer(1000, taskPerformer);
        timer.setRepeats(false);
        timer.start();
        
	}
	
}


