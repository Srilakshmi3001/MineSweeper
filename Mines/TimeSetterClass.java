/**
 * @author [Lakshmi Pinnika]
 * @email [lakshmipinnika1999@gmail.com]
 * @create date 2022-11-13 22:53:32
 * @modify date 2022-11-13 22:53:32
 * @desc [Implement in Java with a GUI the game Minesweeper ]
 */
/*
 Lakshmi Pinnika
Advanced programming concepts
031
999902604
*/
// importing the required packages
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.Timer;

// declaration class name as TimeSetterClass which extends the Action Listener
public class TimeSetterClass implements ActionListener {
	// variable declaration 
	int second;
	int time;
	JLabel label = null;    
	Timer timer = new Timer(1000, this);
	// we are going to setting the time limit for different level players
	public TimeSetterClass(int seconds, JLabel label,int timeLimit) {
		this.second = seconds;
		this.label = label;
		this.time=timeLimit;
		label.setText("" + seconds);
		timer.start();
	}

	// we are able to see the timelimit for each type player. Generally time limit is different for different players.
	
	public void actionPerformed(ActionEvent e) {
		
		second++;
		label.setText("Your Time Limit = "+this.time +" Seconds " +"  "+"Running Time is :- "+ second);
		if (second == (this.time) )
		{
			FreeMineSweeperGame.gameOver();
		}
	}
}
