package state;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class GameSaveUIAction extends GameUIAction implements ActionListener{
	
	private StateLogger gameStateLogger;
	public GameSaveUIAction(StateLogger gameStateLogger){
		 this.gameStateLogger = gameStateLogger;
		 actionType = "Save";
	 }
	
	
	private class GameSaveDo extends GameUIAction.GameDoer{
		
		private GameSaveDo(int slot){
			super(slot, "Save");
		}
	
		@Override
		void onAction() throws IOException {
			gameStateLogger.save(slot);
		}
		
	}


	@Override
	protected ActionListener getActionListener(int index) {
		// TODO Auto-generated method stub
		return new GameSaveDo(index);
	}

}
