package state;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GameLoadUIAction extends GameUIAction implements ActionListener{
	

	public GameLoadUIAction(StateLogger gameStateLogger){
		this.gameStateLogger = gameStateLogger;
		actionType = "Load";
	 }
	
	class GameLoadDo implements ActionListener{
		private int slot;
		public GameLoadDo(int slot){
			this.slot = slot;
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			try {
				gameStateLogger.load(slot);
				JOptionPane.showMessageDialog(new JFrame(), "Load Successful","Dialog",JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JOptionPane.showMessageDialog(new JFrame(), "Game does not exist","Dialog",JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}

	@Override
	protected ActionListener getActionListener(int index) {
		// TODO Auto-generated method stub
		return new GameLoadDo(index);
	}
}
