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
	int Timerem;
	public Time(JLabel passedLabel)
	{
		countdownTimer = new Timer(1000, new CountdownTimerListener());
		this.label = passedLabel;
		Timerem=Main.getTimeRemaining();
	}
	
	public void start()
	{
		countdownTimer.start();
	}
	
	public void reset()
	{
		Timerem=Main.getTimeRemaining();
	}
	
    //A function that is called after every second. It updates the timer and takes other necessary actions
	class CountdownTimerListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if (Timerem > 0)
			{
				displayTime();
				Timerem--;
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
			Main.mainBoard.changeMove();
		}

		private void displayTime() {
			int min, sec;
			min=Timerem/60;
			sec=Timerem%60;
			label.setText(String.valueOf(min)+":"+(sec>=10?String.valueOf(sec):"0"+String.valueOf(sec)));
		}
	}

}