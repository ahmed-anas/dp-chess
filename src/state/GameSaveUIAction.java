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
	
	
	class GameSaveDo implements ActionListener{
		private int slot;
		public GameSaveDo(int slot){
			this.slot = slot;
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			try {
				gameStateLogger.save(slot);
				JOptionPane.showMessageDialog(new JFrame(), "Save Successful","Dialog",JOptionPane.INFORMATION_MESSAGE);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JOptionPane.showMessageDialog(new JFrame(), e.getMessage(),"Dialog",JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}


	@Override
	protected ActionListener getActionListener(int index) {
		// TODO Auto-generated method stub
		return new GameSaveDo(index);
	}

}
