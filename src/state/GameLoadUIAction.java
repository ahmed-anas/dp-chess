package state;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class GameLoadUIAction implements ActionListener{
	

	private StateLogger gameStateLogger;
	public GameLoadUIAction(StateLogger gameStateLogger){
		this.gameStateLogger = gameStateLogger;
	 }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JFrame mainFrame = new JFrame();
		
		JPanel mainPanel = new JPanel();
		
		mainPanel.setLayout(new GridLayout(4,1));
		mainPanel.setBorder(BorderFactory.createTitledBorder(null, "Load Game Slots", TitledBorder.TOP,
				TitledBorder.CENTER, new Font("Lucida Calligraphy", Font.PLAIN, 20), Color.ORANGE));
		
		
		JButton[] slots = new JButton[5];
		
		for(int i = 0; i < slots.length; i++){
			slots[i] = new JButton();
			slots[i].setText("Load " + i);
			slots[i].addActionListener(new GameLoadDo(i));
			mainPanel.add(slots[i]);
			slots[i].setVisible(true);
		}
		mainFrame.add(mainPanel);
		mainFrame.pack();
		mainPanel.setVisible(true);
		mainFrame.setVisible(true);
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
}
