package chess;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.Timer;


/**
 * This is the Time Class.
 * It contains all the required variables and functions related to the timer on the Main GUI
 * It uses a Timer Class
 *
 */

public class Time
{

	private JLabel label;
	Timer countdownTimer;
	int timeRemmaining;
	public Time(JLabel passedLabel)
	{
		countdownTimer = new Timer(1000, new CountdownTimerListener());
		this.label = passedLabel;
		timeRemmaining=Main.getTimeRemaining();
	}
	
	public void start()
	{
		countdownTimer.start();
	}
	
	public void reset()
	{
		timeRemmaining=Main.getTimeRemaining();
	}
	
    //A function that is called after every second. It updates the timer and takes other necessary actions
	private class CountdownTimerListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if (timeRemmaining > 0)
			{
				displayTime();
				timeRemmaining--;
			}
			else
			{
				switchTurn();
			}
		}

		private void switchTurn() {
			label.setText("Time's up!");
			reset();
			start();
			Main game = Main.getGame();
			game.changeMoveWithoutTurn();
			
		}

		private void displayTime() {
			int min, sec;
			min=timeRemmaining/60;
			sec=timeRemmaining%60;
			label.setText(String.valueOf(min)+":"+(sec>=10?String.valueOf(sec):"0"+String.valueOf(sec)));
		}
	}

}