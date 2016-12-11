package state;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;


public abstract class GameUIAction {

	protected StateLogger gameStateLogger;
	protected String actionType;

	public GameUIAction() {
		super();
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JFrame mainFrame = new JFrame();
		
		JPanel mainPanel = new JPanel();
		
		mainPanel.setLayout(new GridLayout(4,1));
		mainPanel.setBorder(BorderFactory.createTitledBorder(null, actionType + " Game Slots", TitledBorder.TOP,
				TitledBorder.CENTER, new Font("Lucida Calligraphy", Font.PLAIN, 20), Color.ORANGE));
		
		
		JButton[] slots = new JButton[5];
		
		for(int i = 0; i < slots.length; i++){
			slots[i] = new JButton();
			slots[i].setText(actionType + " " + i);
			slots[i].addActionListener(getActionListener(i));
			mainPanel.add(slots[i]);
			slots[i].setVisible(true);
		}
		mainFrame.add(mainPanel);
		mainFrame.pack();
		mainPanel.setVisible(true);
		mainFrame.setVisible(true);
	}
	
	protected abstract ActionListener getActionListener(int index);

}