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

public class GameSaveUIAction implements ActionListener{
	
	private StateLogger gameStateLogger;
	public GameSaveUIAction(StateLogger gameStateLogger){
		 this.gameStateLogger = gameStateLogger;
	 }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JFrame mainFrame = new JFrame();
		
		JPanel mainPanel = new JPanel();
		
		mainPanel.setLayout(new GridLayout(4,1));
		mainPanel.setBorder(BorderFactory.createTitledBorder(null, "Save Game Slots", TitledBorder.TOP,
				TitledBorder.CENTER, new Font("Lucida Calligraphy", Font.PLAIN, 20), Color.ORANGE));
		
		
		JButton[] slots = new JButton[5];
		
		for(int i = 0; i < slots.length; i++){
			slots[i] = new JButton();
			slots[i].setText("Save " + i);
			slots[i].addActionListener(new GameSaveDo(i));
			mainPanel.add(slots[i]);
			slots[i].setVisible(true);
		}
		mainFrame.add(mainPanel);
		mainFrame.pack();
		mainPanel.setVisible(true);
		mainFrame.setVisible(true);
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

}
