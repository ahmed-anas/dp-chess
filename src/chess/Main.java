package chess;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import pieces.*;
import state.GameLoadUIAction;
import state.GameSaveUIAction;
import state.MoveSequence;
import state.StateLogger;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * @author Ashish Kedia and Adarsh Mohata
 *
 */

/**
 * This is the Main Class of our project. All GUI Elements are declared,
 * initialized and used in this class itself. It is inherited from the JFrame
 * Class of Java's Swing Library.
 * 
 */
 
public class Main implements TimeObserver
{
	private static final long serialVersionUID = 1L;
	private static final int WHITE_COLOUR=0;
	private static final int BLACK_COLOUR=1;
	private static final int HEIGHT = 700;
	private static final int WIDTH = 1110;
	private static Rook wr01, wr02, br01, br02;
	private static Knight wk01, wk02, bk01, bk02;
	private static Bishop wb01, wb02, bb01, bb02;
	private static Pawn wp[], bp[];
	private static Queen wq, bq;
	private static King wk, bk;
	private Cell selectedCell, previous;
	private int chance = 0;
	private Board data = new Board();
	private ArrayList<Cell> destinationList = new ArrayList<Cell>();
	private Player whitePlayer,blackPlayer;

	private JPanel board;
	public JPanel getBoard() {
		return board;
	}

	private JPanel whiteDetails;
	private JPanel blackDetails;
	private JPanel whiteComboPanel;
	private JPanel blackComboPanel;
	private JPanel controlPanel, whitePlayerPanel, blackPlayerPanel, temp, displayTime, showPlayer, time;
	private JSplitPane split;
	private JLabel label, mov;
	private static JLabel currentMoveLabel;
	private Time timer;
	private static Main game;
	private boolean selected = false, end = false;
	private Container content;
	private ArrayList<Player> wPlayers, bPlayers;
	private ArrayList<String> Wnames;
	private ArrayList<String> Bnames;
	private JComboBox<String> wcombo, bcombo;
	private String wname, bname, winnerName;
	private static String move;
	private Player tempPlayer;
	private JScrollPane wscroll, bscroll;
	private String[] WNames = {}, BNames = {};
	private JSlider timeSlider;
	private BufferedImage image;
	private Button start, wselect, bselect, WNewPlayer, BNewPlayer;
	private JButton restart,quit,settings;
	private static int timeRemaining;
	private static JFrame chessBoard;
	private StateLogger stateLogger;
	
	private JButton saveGame;
	private JButton loadGame;
	

	public Player getWhitePlayer() {
		return whitePlayer;
	}

	public void setWhitePlayer(Player whitePlayer) {
		this.whitePlayer = whitePlayer;
		stateLogger.setWhitePlayer(whitePlayer);
	}

	public Player getBlackPlayer() {
		return blackPlayer;
	}

	public void setBlackPlayer(Player blackPlayer) {
		this.blackPlayer = blackPlayer;
		stateLogger.setBlackPlayer(blackPlayer);
	}
	
	public static void main(String[] args)
	{
		startGame();
	}

	private static void startGame()
	{	
		restartGame();
	}

	private static void intializePieces()
	{
		wr01 = new Rook("WR01", ImagePath.WHITE_ROOK, WHITE_COLOUR);
		wr02 = new Rook("WR02", ImagePath.WHITE_ROOK, WHITE_COLOUR);
		br01 = new Rook("BR01",ImagePath.BLACK_ROOK, BLACK_COLOUR);
		br02 = new Rook("BR02", ImagePath.BLACK_ROOK, BLACK_COLOUR);
		wk01 = new Knight("WK01", ImagePath.WHITE_KNIGHT, WHITE_COLOUR);
		wk02 = new Knight("WK02", ImagePath.WHITE_KNIGHT, WHITE_COLOUR);
		bk01 = new Knight("BK01", ImagePath.BLACK_KNIGHT, BLACK_COLOUR);
		bk02 = new Knight("BK02", ImagePath.BLACK_KNIGHT, BLACK_COLOUR);
		wb01 = new Bishop("WB01", ImagePath.WHITE_BISHOP, WHITE_COLOUR);
		wb02 = new Bishop("WB02", ImagePath.WHITE_BISHOP, WHITE_COLOUR);
		bb01 = new Bishop("BB01", ImagePath.BLACK_BISHOP, BLACK_COLOUR);
		bb02 = new Bishop("BB02", ImagePath.BLACK_BISHOP, BLACK_COLOUR);
		wq = new Queen("WQ", ImagePath.WHITE_QUEEN, WHITE_COLOUR);
		bq = new Queen("BQ", ImagePath.BLACK_QUEEN, BLACK_COLOUR);
		wk = new King("WK", ImagePath.WHITE_KING, WHITE_COLOUR, 7, 3);
		bk = new King("BK", ImagePath.BLACK_KING, BLACK_COLOUR, 0, 3);
		wp = new Pawn[8];
		bp = new Pawn[8];
		for (int i = 0; i < 8; i++)
		{
			wp[i] = new Pawn("WP0" + (i + 1), ImagePath.WHITE_PAWN, WHITE_COLOUR);
			bp[i] = new Pawn("BP0" + (i + 1), ImagePath.BLACK_PAWN, BLACK_COLOUR);
		}
	}

	// Constructor
	private Main()
	{
		this.stateLogger = new StateLogger();
		move = "White";
		intializeTimer();
		fetchPlayers();
		setUpUI();	
	}

	private void setUpUI()
	{
		chessBoard = new JFrame();
		whiteDetails = new JPanel(new GridLayout(3, 3));
		blackDetails = new JPanel(new GridLayout(3, 3));
		blackComboPanel = new JPanel();
		whiteComboPanel = new JPanel();
		board = new JPanel(new GridLayout(8, 8));
		board.setMinimumSize(new Dimension(800, 700));
		board.setBorder(BorderFactory.createLoweredBevelBorder());
		ImageIcon img = new ImageIcon(this.getClass().getResource("icon.png"));
		chessBoard.setIconImage(img.getImage());
		content = chessBoard.getContentPane();
		chessBoard.setSize(WIDTH, HEIGHT);
		chessBoard.setTitle("Chess");
		content.setBackground(Color.black);
		content.setLayout(new BorderLayout());
		
		// Defining the Player Box in Control Panel
		whitePlayerPanel = new JPanel();
		whitePlayerPanel.setBorder(BorderFactory.createTitledBorder(null, "White Player", TitledBorder.TOP,
				TitledBorder.CENTER, new Font("times new roman", Font.BOLD, 18), Color.RED));
		whitePlayerPanel.setLayout(new BorderLayout());

		blackPlayerPanel = new JPanel();
		blackPlayerPanel.setBorder(BorderFactory.createTitledBorder(null, "Black Player", TitledBorder.TOP,
				TitledBorder.CENTER, new Font("times new roman", Font.BOLD, 18), Color.BLUE));
		blackPlayerPanel.setLayout(new BorderLayout());

		JPanel whitestats = new JPanel(new GridLayout(3, 3));
		JPanel blackstats = new JPanel(new GridLayout(3, 3));
		wcombo = new JComboBox<String>(WNames);
		wscroll = new JScrollPane(wcombo);
		bcombo = new JComboBox<String>(BNames);
		bscroll = new JScrollPane(bcombo);
		
		wselect = new Button("Select");
		bselect = new Button("Select");
		wselect.addActionListener(new SelectHandler(0));
		bselect.addActionListener(new SelectHandler(1));
		WNewPlayer = new Button("New Player");
		BNewPlayer = new Button("New Player");
		WNewPlayer.addActionListener(new Handler(0));
		BNewPlayer.addActionListener(new Handler(1));
		whiteComboPanel.setLayout(new FlowLayout());
		whiteComboPanel.add(wscroll);
		whiteComboPanel.add(wselect);
		whiteComboPanel.add(WNewPlayer);
		blackComboPanel.setLayout(new FlowLayout());
		blackComboPanel.add(bscroll);
		blackComboPanel.add(bselect);
		blackComboPanel.add(BNewPlayer);
		whitePlayerPanel.add(whiteComboPanel, BorderLayout.NORTH);
		blackPlayerPanel.add(blackComboPanel, BorderLayout.NORTH);
		whitestats.add(new JLabel("Name   :"));
		whitestats.add(new JLabel("Played :"));
		whitestats.add(new JLabel("Won    :"));
		blackstats.add(new JLabel("Name   :"));
		blackstats.add(new JLabel("Played :"));
		blackstats.add(new JLabel("Won    :"));
		whitePlayerPanel.add(whitestats, BorderLayout.WEST);
		blackPlayerPanel.add(blackstats, BorderLayout.WEST);
		setUpPieces();
		showPlayer = new JPanel(new FlowLayout());
		showPlayer.add(timeSlider);
		JLabel setTime = new JLabel("Set Timer(in mins):");
		start = new Button("Start");
		start.setBackground(Color.black);
		start.setForeground(Color.white);
		start.addActionListener(new Start());
		start.setPreferredSize(new Dimension(120, 40));
		setTime.setFont(new Font("Arial", Font.BOLD, 16));
		label = new JLabel("Time Starts now", JLabel.CENTER);
		label.setFont(new Font("SERIF", Font.BOLD, 30));
		displayTime = new JPanel(new FlowLayout());
		time = new JPanel(new GridLayout(3, 3));
		time.add(setTime);
		time.add(showPlayer);
		displayTime.add(start);
		time.add(displayTime);
		// The Left Layout When Game is inactive
		temp = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			public void paintComponent(Graphics g)
			{
				try
				{
					image = ImageIO.read(this.getClass().getResource("clash.jpg"));
				} catch (IOException ex)
				{
					System.out.println("not found");
				}

				g.drawImage(image, 0, 0, null);
			}
		};

		temp.setMinimumSize(new Dimension(800, 700));
		setUpControlPanel();
		split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, temp, controlPanel);

		content.add(split);
		chessBoard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		chessBoard.setVisible(true);
		chessBoard.setResizable(false);
	}
	private void setUpControlPanel()
	{
		controlPanel = new JPanel();
		controlPanel.setLayout(new GridLayout(4,1));
		controlPanel.setBorder(BorderFactory.createTitledBorder(null, "Statistics", TitledBorder.TOP,
				TitledBorder.CENTER, new Font("Lucida Calligraphy", Font.PLAIN, 20), Color.ORANGE));
		controlPanel.add(whitePlayerPanel);
		controlPanel.add(blackPlayerPanel);
		controlPanel.add(time);
		JPanel options=new JPanel(new FlowLayout(FlowLayout.CENTER));
		restart=new JButton("Restart");
		quit=new JButton("Quit");
		settings=new JButton("Settings");
		
		saveGame = new JButton("Save Game");
		loadGame = new JButton("Load Game");
		Start buttonHandler=new Start();
		restart.addActionListener(buttonHandler);
		quit.addActionListener(buttonHandler);
		settings.addActionListener(buttonHandler);
		saveGame.addActionListener(new GameSaveUIAction(this.stateLogger));
		loadGame.addActionListener(new GameLoadUIAction(this.stateLogger));
		
		options.add(settings);
		options.add(restart);
		options.add(quit);
		options.add(saveGame);
		options.add(loadGame);
		
		controlPanel.add(options);
		controlPanel.setMinimumSize(new Dimension(285, 700));

	}
	private void setUpPieces()
	{
		intializePieces();
		data.setBoardState(new Cell[8][8]);
		Cell cell;
		Piece P;
		MouseHandler mouseHandler=new MouseHandler();
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
			{
				P = null;
				if (i == 0 && j == 0)
					P = br01;
				else if (i == 0 && j == 7)
					P = br02;
				else if (i == 7 && j == 0)
					P = wr01;
				else if (i == 7 && j == 7)
					P = wr02;
				else if (i == 0 && j == 1)
					P = bk01;
				else if (i == 0 && j == 6)
					P = bk02;
				else if (i == 7 && j == 1)
					P = wk01;
				else if (i == 7 && j == 6)
					P = wk02;
				else if (i == 0 && j == 2)
					P = bb01;
				else if (i == 0 && j == 5)
					P = bb02;
				else if (i == 7 && j == 2)
					P = wb01;
				else if (i == 7 && j == 5)
					P = wb02;
				else if (i == 0 && j == 3)
					P = bk;
				else if (i == 0 && j == 4)
					P = bq;
				else if (i == 7 && j == 3)
					P = wk;
				else if (i == 7 && j == 4)
					P = wq;
				else if (i == 1)
					P = bp[j];
				else if (i == 6)
					P = wp[j];
				cell = new Cell(i, j, P);
				cell.addMouseListener(mouseHandler);
				board.add(cell);
				data.getBoardState()[i][j] = cell;
			}
	}

	private void intializeTimer()
	{
		timeRemaining = 60;
		timeSlider = new JSlider();
		timeSlider.setMinimum(1);
		timeSlider.setMaximum(15);
		timeSlider.setValue(1);
		timeSlider.setMajorTickSpacing(2);
		timeSlider.setPaintLabels(true);
		timeSlider.setPaintTicks(true);
		timeSlider.addChangeListener(new TimeChange());
	}

	private void fetchPlayers()
	{
		String name=null;
		Wnames = new ArrayList<String>();
		Bnames = new ArrayList<String>();
		bPlayers= wPlayers = Player.fetchPlayers();
		Iterator<Player> itr = wPlayers.iterator();
		while (itr.hasNext())
		{
			name= itr.next().name();
			Wnames.add(name);
			Bnames.add(name);
		}	
		
		WNames = Wnames.toArray(WNames);
		BNames = Bnames.toArray(BNames);
	}

	public void changeMove()
	{
		int kingXCoordinate=getKing(chance).getx();
		int kingYCoordinate=getKing(chance).gety();
		toggleMove();
		if (data.getBoardState()[kingXCoordinate][kingYCoordinate].ischeck())
		{
			gameend();
		}	
		cleandestinations();
		if (previous != null)
			previous.deselect();
		previous = null;
		
		if (!end && timer != null)
		{
			setUpNextMove();
		}
		
		SoundPlayer.playBeep();
	}
	
	public void changeMoveWithoutTurn(){
		changeMove();
		this.stateLogger.switchPlayerWithoutTurn();
	}

	private void setUpNextMove()
	{
		timer.reset();
		timer.start();
		showPlayer.remove(currentMoveLabel);
		if (move == "White")
			move = "Black";
		else
			move = "White";
		currentMoveLabel.setText(Main.move);
		showPlayer.add(currentMoveLabel);
	}

	private void toggleMove()
	{
		chance ^= 1;
	}
	public static Main getGame()
	{
		return game;
	}
	// A function to retrieve the Black King or White King
	private Cell[][] getBoardStateCopy()
	{
		Cell newboardstate[][] = new Cell[8][8];
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
			{
				try
				{
					newboardstate[i][j] = new Cell(data.getBoardState()[i][j]);
				} catch (CloneNotSupportedException e)
				{
					e.printStackTrace();
				}
			}
		return newboardstate;
	}
	private King getKing(int color)
	{
		if (color == WHITE_COLOUR)
			return wk;
		else
			return bk;
	}

	// A function to clean the highlights of possible destination cells
	private void cleandestinations()
	{
		ListIterator<Cell> it = destinationList.listIterator();
		while (it.hasNext())
			it.next().removepossibledestination();
	}
	// A function that indicates the possible moves by highlighting the Cells
	private void highlightdestinations()
	{
		ListIterator<Cell> it = destinationList.listIterator();
		while (it.hasNext())
			it.next().setpossibledestination();
	}

	// Function to check if the king will be in danger if the given move is made
	private boolean willKingBeInDanger(Cell fromcell, Cell tocell)
	{
		Cell newboardstate[][] = getBoardStateCopy();

		if (newboardstate[tocell.getCellX()][tocell.getCellY()].getpiece() != null)
			newboardstate[tocell.getCellX()][tocell.getCellY()].removePiece();

		newboardstate[tocell.getCellX()][tocell.getCellY()].setPiece(newboardstate[fromcell.getCellX()][fromcell.getCellY()].getpiece());
		if (newboardstate[tocell.getCellX()][tocell.getCellY()].getpiece() instanceof King)
		{
			((King) (newboardstate[tocell.getCellX()][tocell.getCellY()].getpiece())).setx(tocell.getCellX());
			((King) (newboardstate[tocell.getCellX()][tocell.getCellY()].getpiece())).sety(tocell.getCellY());
		}
		newboardstate[fromcell.getCellX()][fromcell.getCellY()].removePiece();
		return (((King) (newboardstate[getKing(chance).getx()][getKing(chance).gety()].getpiece())).isindanger(newboardstate));
	}

	// A function to eliminate the possible moves that will put the King in
	// danger
	private ArrayList<Cell> filterdestination(Cell fromcell)
	{
		ArrayList<Cell> newlist = new ArrayList<Cell>();
		Cell newboardstate[][]=null;
		ListIterator<Cell> it = destinationList.listIterator();
		int x, y;
		while (it.hasNext())
		{
			newboardstate=getBoardStateCopy();

			Cell tempc = it.next();
			if (newboardstate[tempc.getCellX()][tempc.getCellY()].getpiece() != null)
				newboardstate[tempc.getCellX()][tempc.getCellY()].removePiece();
			newboardstate[tempc.getCellX()][tempc.getCellY()].setPiece(newboardstate[fromcell.getCellX()][fromcell.getCellY()].getpiece());
			x = getKing(chance).getx();
			y = getKing(chance).gety();
			if (newboardstate[fromcell.getCellX()][fromcell.getCellY()].getpiece() instanceof King)
			{
				((King) (newboardstate[tempc.getCellX()][tempc.getCellY()].getpiece())).setx(tempc.getCellX());
				((King) (newboardstate[tempc.getCellX()][tempc.getCellY()].getpiece())).sety(tempc.getCellY());
				x = tempc.getCellX();
				y = tempc.getCellY();
			}
			newboardstate[fromcell.getCellX()][fromcell.getCellY()].removePiece();
			if ((((King) (newboardstate[x][y].getpiece())).isindanger(newboardstate) == false))
				newlist.add(tempc);
		}
		return newlist;
	}

	// A Function to filter the possible moves when the king of the current
	// player is under Check
	private ArrayList<Cell> incheckfilter(ArrayList<Cell> destlist, Cell fromcell, int color)
	{
		ArrayList<Cell> newlist = new ArrayList<Cell>();
		Cell newboardstate[][];
		ListIterator<Cell> it = destlist.listIterator();
		int x, y;
		while (it.hasNext())
		{
			newboardstate=getBoardStateCopy();
			Cell tempc = it.next();
			if (newboardstate[tempc.getCellX()][tempc.getCellY()].getpiece() != null)
				newboardstate[tempc.getCellX()][tempc.getCellY()].removePiece();
			newboardstate[tempc.getCellX()][tempc.getCellY()].setPiece(newboardstate[fromcell.getCellX()][fromcell.getCellY()].getpiece());
			x = getKing(color).getx();
			y = getKing(color).gety();
			if (newboardstate[tempc.getCellX()][tempc.getCellY()].getpiece() instanceof King)
			{
				((King) (newboardstate[tempc.getCellX()][tempc.getCellY()].getpiece())).setx(tempc.getCellX());
				((King) (newboardstate[tempc.getCellX()][tempc.getCellY()].getpiece())).sety(tempc.getCellY());
				x = tempc.getCellX();
				y = tempc.getCellY();
			}
			newboardstate[fromcell.getCellX()][fromcell.getCellY()].removePiece();
			if ((((King) (newboardstate[x][y].getpiece())).isindanger(newboardstate) == false))
				newlist.add(tempc);
		}
		return newlist;
	}

	// A function to check if the King is check-mate. The Game Ends if this
	// function returns true.
	public boolean checkmate(int color)
	{
		ArrayList<Cell> dlist = new ArrayList<Cell>();
		for (int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 8; j++)
			{
				if (data.getBoardState()[i][j].getpiece() != null && data.getBoardState()[i][j].getpiece().getcolor() == color)
				{
					dlist.clear();
					dlist = data.getBoardState()[i][j].getpiece().move(data.getBoardState(), new Coordinates(i,j));
					dlist = incheckfilter(dlist, data.getBoardState()[i][j], color);
					if (dlist.size() != 0)
						return false;
				}
			}
		}
		return true;
	}

	private void gameend()
	{
		cleandestinations();
		displayTime.setEnabled(false);
		timer.countdownTimer.stop();
		if (previous != null)
			previous.removePiece();
		if (chance == WHITE_COLOUR)
		{
			updateWinner(whitePlayer);
		} else
		{
			updateWinner(blackPlayer);
		}
		updateEndGameUI();
		end = true;
		restartGame();
	}

	public static void restartGame()
	{
		if(chessBoard!=null)
			chessBoard.dispose();
		game=new Main();
		
		
	}
	
	private void updateEndGameUI()
	{
		JOptionPane.showMessageDialog(board, "Checkmate!!!\n" + winnerName + " wins");
		whitePlayerPanel.remove(whiteDetails);
		blackPlayerPanel.remove(blackDetails);
		displayTime.remove(label);

		displayTime.add(start);
		showPlayer.remove(mov);
		showPlayer.remove(currentMoveLabel);
		showPlayer.revalidate();
		showPlayer.add(timeSlider);

		split.remove(board);
		split.add(temp);
		WNewPlayer.setEnabled(false);
		BNewPlayer.setEnabled(false);
		wselect.setEnabled(false);
		bselect.setEnabled(false);
		
		chessBoard.setEnabled(false);
		chessBoard.setEnabled(false);
		
	}
	
	public StateLogger getStateLogger(){
		return this.stateLogger;
	}
	private void updateWinner(Player winner)
	{
		winner.updateGamesWon();
		winner.Update_Player();
	    winnerName=winner.name();
	}

	// These are the abstract function of the parent class. Only relevant method
	// here is the On-Click Fuction
	// which is called when the user clicks on a particular cell
	
	private void updateDistinationList()
	{
		selectedCell.select();
		destinationList.clear();
		destinationList = selectedCell.getpiece().move(data.getBoardState(), selectedCell.getCoordinates());
		if (selectedCell.getpiece() instanceof King)
			destinationList = filterdestination(selectedCell);
		else
		{
			if (data.getBoardState()[getKing(chance).getx()][getKing(chance).gety()].ischeck())
				destinationList = new ArrayList<Cell>(filterdestination(selectedCell));
			else if (destinationList.isEmpty() == false && willKingBeInDanger(selectedCell, destinationList.get(0)))
				destinationList.clear();
		}
	}

	private King getOpponentKing()
	{
		return getKing(chance ^ 1);
	}

	// Other Irrelevant abstract function. Only the Click Event is captured.
	
	public void doMove(int x, int y){
		selectedCell = this.data.getBoardState()[x][y];
		
		if (previous == null)
		{
			if (selectedCell.getpiece() != null)
			{
				if (selectedCell.getpiece().getcolor() != chance)
					return;
				previous = selectedCell;
				
				updateDistinationList();
				highlightdestinations();
			}
		} else
		{
			if (selectedCell.getCellX() == previous.getCellX() && selectedCell.getCellY() == previous.getCellY())
			{
				selectedCell.deselect();
				cleandestinations();
				destinationList.clear();
				previous = null;
			} else if (selectedCell.getpiece() == null || previous.getpiece().getcolor() != selectedCell.getpiece().getcolor())
			{
				if (selectedCell.ispossibledestination())
				{
					if (selectedCell.getpiece() != null)
						selectedCell.removePiece();
					selectedCell.setPiece(previous.getpiece());
					if (previous.ischeck())
						previous.removecheck();
					previous.removePiece();
					if (getOpponentKing().isindanger(data.getBoardState()))
					{
						data.getBoardState()[getOpponentKing().getx()][getOpponentKing().gety()].setcheck();
						if (checkmate(getOpponentKing().getcolor()))
						{
							previous.deselect();
							if (previous.getpiece() != null)
								previous.removePiece();
							gameend();
						}
					}
					if (getKing(chance).isindanger(data.getBoardState()) == false)
						data.getBoardState()[getKing(chance).getx()][getKing(chance).gety()].removecheck();
					if (selectedCell.getpiece() instanceof King)
					{
						((King) selectedCell.getpiece()).setx(selectedCell.getCellX());
						((King) selectedCell.getpiece()).sety(selectedCell.getCellY());
					}
					stateLogger.addNewMove(previous, selectedCell);
					changeMove();
					
					if (!end)
					{
						timer.reset();
						timer.start();
					}
				}
				if (previous != null)
				{
					previous.deselect();
					previous = null;
				}
				cleandestinations();
				destinationList.clear();
			} else if (previous.getpiece().getcolor() == selectedCell.getpiece().getcolor())
			{
				previous.deselect();
				cleandestinations();
				previous = selectedCell;
				
				updateDistinationList();
				highlightdestinations();
			}
		}
		if (selectedCell.getpiece() != null && selectedCell.getpiece() instanceof King)
		{
			((King) selectedCell.getpiece()).setx(selectedCell.getCellX());
			((King) selectedCell.getpiece()).sety(selectedCell.getCellY());
		}
	}
	private class MouseHandler extends MouseAdapter
	{
		@Override
		public void mouseClicked(MouseEvent event)
		{
			super.mouseClicked(event);
			selectedCell = (Cell) event.getSource();
			doMove(selectedCell.getCellX(), selectedCell.getCellY());
		}
	}
	
	private class Start implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			if (event.getSource()==start)
			{
				if (whitePlayer == null || blackPlayer == null)
				{
					JOptionPane.showMessageDialog(controlPanel, "Fill in the details");
					return;
				}
				updateUIForChessGame();
				updateGamesPlayed();
			}
			else if (event.getSource()==restart)
			{
				restartGame();
			}
			else if (event.getSource()==quit)
			{
				System.exit(0);
			}
			else if(event.getSource()==settings)
			{
				
			}
		}

		
	}
	public void updateUIForChessGame()
	{
		
		WNewPlayer.setEnabled(false);
		BNewPlayer.setEnabled(false);
		wselect.setEnabled(false);
		bselect.setEnabled(false);
		split.remove(temp);
		split.add(board);
		showPlayer.remove(timeSlider);
		mov = new JLabel("Move:");
		mov.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		mov.setForeground(Color.red);
		showPlayer.add(mov);
		currentMoveLabel = new JLabel(move);
		currentMoveLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		currentMoveLabel.setForeground(Color.blue);
		showPlayer.add(currentMoveLabel);
		displayTime.remove(start);
		displayTime.add(label);
		timer = new Time(label);
		timer.start();
	}
	
	private void updateGamesPlayed(){
		whitePlayer.updateGamesPlayed();
		whitePlayer.Update_Player();
		blackPlayer.updateGamesPlayed();
		blackPlayer.Update_Player();
	}

	private class TimeChange implements ChangeListener
	{
		@Override
		public void stateChanged(ChangeEvent arg0)
		{
			timeRemaining = timeSlider.getValue() * 60;
		}
	}

	private class SelectHandler implements ActionListener
	{
		private int color;

		SelectHandler(int i)
		{
			color = i;
		}

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			tempPlayer = null;
			String n = (color == WHITE_COLOUR) ? wname : bname;
			JComboBox<String> jc = (color == WHITE_COLOUR) ? wcombo : bcombo;
			JComboBox<String> ojc = (color == WHITE_COLOUR) ? bcombo : wcombo;
			ArrayList<Player> pl = (color == WHITE_COLOUR) ? wPlayers : bPlayers;

			ArrayList<Player> opl = Player.fetchPlayers();
			if (opl.isEmpty())
				return;
			JPanel det = (color == WHITE_COLOUR) ? whiteDetails : blackDetails;
			JPanel PL = (color == WHITE_COLOUR) ? whitePlayerPanel : blackPlayerPanel;
			if (selected == true)
				det.removeAll();
			n = (String) jc.getSelectedItem();
			Iterator<Player> it = pl.iterator();
			Iterator<Player> oit = opl.iterator();
			while (it.hasNext())
			{
				Player p = it.next();
				if (p.name().equals(n))
				{
					tempPlayer = p;
					break;
				}
			}
			while (oit.hasNext())
			{
				Player p = oit.next();
				if (p.name().equals(n))
				{
					opl.remove(p);
					break;
				}
			}

			if (tempPlayer == null)
				return;
			if (color == WHITE_COLOUR)
			{
				setWhitePlayer(tempPlayer);
			}
			else
			{
				setBlackPlayer(tempPlayer);
			}
				
			bPlayers = opl;
			ojc.removeAllItems();
			for (Player s : opl)
				ojc.addItem(s.name());
			det.add(new JLabel(" " + tempPlayer.name()));
			det.add(new JLabel(" " + tempPlayer.gamesplayed()));
			det.add(new JLabel(" " + tempPlayer.gameswon()));

			PL.revalidate();
			PL.repaint();
			PL.add(det);
			selected = true;
		}

	}

	private class Handler implements ActionListener
	{
		private int color;

		Handler(int i)
		{
			color = i;
		}

		@Override
		public void actionPerformed(ActionEvent e)
		{
			// TODO Auto-generated method stub
			String n = (color == 0) ? wname : bname;
			JPanel j = (color == 0) ? whitePlayerPanel : blackPlayerPanel;
			ArrayList<Player> N = Player.fetchPlayers();
			Iterator<Player> it = N.iterator();
			JPanel det = (color == 0) ? whiteDetails : blackDetails;
			n = JOptionPane.showInputDialog(j, "Enter your name");

			if (n != null)
			{

				while (it.hasNext())
				{
					if (it.next().name().equals(n))
					{
						JOptionPane.showMessageDialog(j, "Player exists");
						return;
					}
				}

				if (n.length() != 0)
				{
					Player tem = new Player(n);
					tem.Update_Player();
					if (color == 0)
					{
						setWhitePlayer(tem);
					}
					else
					{
						setBlackPlayer(tem);
					}
						
				} else
					return;
			} else
				return;
			det.removeAll();
			det.add(new JLabel(" " + n));
			det.add(new JLabel(" 0"));
			det.add(new JLabel(" 0"));
			j.revalidate();
			j.repaint();
			j.add(det);
			selected = true;
		}
	}

	private class ImagePath
	{
		private final static String WHITE_KING = "White_King.png";
		private final static String BLACK_KING = "Black_King.png";
		private final static String WHITE_QUEEN = "White_Queen.png";
		private final static String BLACK_QUEEN = "Black_Queen.png";
		private final static String WHITE_ROOK = "White_Rook.png";
		private final static String BLACK_ROOK = "Black_Rook.png";
		private final static String WHITE_BISHOP = "White_Bishop.png";
		private final static String BLACK_BISHOP = "Black_Bishop.png";
		private final static String WHITE_KNIGHT = "White_Knight.png";
		private final static String BLACK_KNIGHT = "Black_Knight.png";
		private final static String WHITE_PAWN = "White_Pawn.png";
		private final static String BLACK_PAWN = "Black_Pawn.png";
	}

	public static int getTimeRemaining()
	{
		return timeRemaining;
	}

	@Override
	public void update()
	{
		changeMoveWithoutTurn();
	}

	
}