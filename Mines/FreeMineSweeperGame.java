/**
 * @author [Lakshmi Pinnika]
 * @email [lakshmipinnika1999@gmail.com]
 * @create date 2022-11-13 22:44:01
 * @modify date 2022-11-13 22:44:01
 * @desc [Implement in Java with a GUI the game Minesweeper ]
 */
/*
 Lakshmi Pinnika
Advanced programming concepts
031
999902604
*/
// importing the required packages 
import java.io.File;
import java.text.*;
import java.util.Calendar;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import java.awt.*;
import java.awt.event.*;

// declaration of class
public class FreeMineSweeperGame  {
// variable declaration
	static int boardHeight, boardWidth, mines, temp=0;
	static JFrame mineSweeperBoard 	= 	new JFrame();
	static JPanel mainPanel	= new JPanel(new FlowLayout());
	static JPanel board;
	static JLabel message=new JLabel("Select Your Level to Play Minesweeper :- ");
	static JPanel mineBoard = new JPanel();
	static JButton[][] mineSweeperBoardButtons = new JButton[0][0];
	static JLabel timeCount = new JLabel();
	// we are going to use images for game
	static ImageIcon iconOne = new ImageIcon("mine1.jpg");
	static ImageIcon iconTwo = new ImageIcon("mine2.jpg");
	static ImageIcon rightClick = new ImageIcon("mineMark.png");
	
	
	/**
	 * constructor to start the game board
	 */
	// In this method we are going the level of the player like beginner , intermediate and advance
	public FreeMineSweeperGame() {
		
		JButton beginner 	 =	 new JButton("Beginner");
		JButton intermediate =	 new JButton("Intermediate");
		JButton advance 	 = 	 new JButton("Advance");
		
		JButton wallImg = new JButton();
		ImageIcon logo = new ImageIcon("wall.png");
		wallImg.setIcon(logo);
		mainPanel.add(message);
		mainPanel.add(beginner);		
		mainPanel.add(intermediate);		
		mainPanel.add(advance);
		mainPanel.add(wallImg);
		// we are going to set gameboard size
		mineSweeperBoard.setSize(900,600);
		
		mineSweeperBoard.add(mainPanel);
		mineSweeperBoard.setVisible(true);
		beginner.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {				
				boardHeight = 7;
				boardWidth = 9;
				mines = 10;
				mineSweeperBoard(boardHeight, boardWidth, mines);
				new TimeSetterClass(0, timeCount, 60);
			}
		});
		intermediate.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {				
				boardHeight = 13;
				boardWidth = 18;
				mines = 35;
				mineSweeperBoard(boardHeight, boardWidth, mines);
				new TimeSetterClass(0, timeCount,180);
			}
		});
		advance.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				boardHeight = 22;
				boardWidth = 25;
				mines = 91;
				mineSweeperBoard(boardHeight, boardWidth, mines);
				new TimeSetterClass(0, timeCount,600);
			}
		});

	}

	/**
	 * create game board
	 * @param sizeBoardTypeRow
	 * @param sizeBoardTypeCol
	 * @param count
	 */
	static void mineSweeperBoard(int sizeBoardTypeRow, int sizeBoardTypeCol, int count) {
		board		 = new JPanel(new GridLayout(sizeBoardTypeRow, sizeBoardTypeRow));
		mineSweeperBoardButtons = new JButton[sizeBoardTypeRow][sizeBoardTypeCol];
		mainPanel.removeAll();
		mineSweeperBoard.remove(mainPanel);
		mainPanel = new JPanel(new BorderLayout());
		gameTime();

		for (int i = 0; i < sizeBoardTypeRow; i++) {
			for (int j = 0; j < sizeBoardTypeCol; j++) {
				JButton button = new JButton();

				mineSweeperBoardButtons[i][j] = button;
				mineSweeperBoardButtons[i][j].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
				mineSweeperBoardButtons[i][j].setBackground(Color.PINK);
				mineSweeperBoardButtons[i][j].setForeground(Color.PINK);
				board.add(mineSweeperBoardButtons[i][j]);
			}
		}
		mainPanel.add(board);
		setMinesOnBoard(sizeBoardTypeRow, sizeBoardTypeCol, count);
		int fsize = sizeBoardTypeRow * 62;
		mineSweeperBoard.setSize(fsize, fsize);
		Player.mineCount(sizeBoardTypeRow, sizeBoardTypeCol);
		mineSweeperBoard.add(mainPanel);
		mineSweeperBoard.setVisible(true);
	}

	/**
	 * setting up game timer
	 */
	private static void gameTime() {
		
		JToolBar timeRunner = new JToolBar();
		timeRunner.setFloatable(false);
		mainPanel.add(timeRunner, BorderLayout.PAGE_START);
		timeRunner.add(timeCount);
	}

	
	/**
	 * to end the game checking the conditions
	 */
	public static void gameOver() {

		for (int rowCount = 0; rowCount <boardHeight; rowCount++) {
			for (int colCount = 0; colCount <boardWidth; colCount++) {
				if (mineSweeperBoardButtons[rowCount][colCount].getIcon() != null) {
					if (mineSweeperBoardButtons[rowCount][colCount].getIcon().equals(iconTwo)
							&& mineSweeperBoardButtons[rowCount][colCount].getText().isEmpty()) {
						mineSweeperBoardButtons[rowCount][colCount].setIcon(iconOne);
						mineSweeperBoardButtons[rowCount][colCount].setBackground(Color.WHITE);
					}
					if(mineSweeperBoardButtons[rowCount][colCount].getIcon().equals(rightClick) && !mineSweeperBoardButtons[rowCount][colCount].getText().isEmpty()) {
						mineSweeperBoardButtons[rowCount][colCount].setIcon(null);
						mineSweeperBoardButtons[rowCount][colCount].setText("X");
						mineSweeperBoardButtons[rowCount][colCount].setBackground(Color.white);
						mineSweeperBoardButtons[rowCount][colCount].setForeground(Color.red);
					}

				}
			}
		}
		
		blastingTone();
		
		JOptionPane.showMessageDialog(null, "GameOver\n");
		
		mainPanel.removeAll();
		System.exit(0);

	}
	
	/**
	 * make blasting sound when mines are exploded
	 */
	public static void blastingTone() {
	    try {
	        AudioInputStream input = AudioSystem.getAudioInputStream(new File("explosion.wav").getAbsoluteFile());
	        Clip clip = AudioSystem.getClip();
	        clip.open(input);
	        clip.start();
	    } catch(Exception ex) {}
	}
	
	/**
	 * check for player winning
	 */
	public static void gameWin() {

		int cellCount = 0;
		for (int rowCount = 0; rowCount < boardHeight; rowCount++) {
			for (int colCount = 0; colCount < boardWidth; colCount++) {
				if (mineSweeperBoardButtons[rowCount][colCount].getBackground().equals(Color.WHITE)) {
					cellCount++;
				}
			}
		}
		if (cellCount == (boardHeight * boardWidth) - mines) {
			JOptionPane.showMessageDialog(null, " YOU WIN!\r\n" + 
					"\r\n" + 
					"Congratulations on Winning MineSweeper!");
			mainPanel.removeAll();
			System.exit(0);
		}
	}

	/**
	 * return number of mines adjacent to the given cell
	 * @param rowCount
	 * @param colCount
	 * @return
	 */
	static int numberOfMines(int rowCount, int colCount) {
		if (rowCount < 0 || colCount<0 || colCount>= boardWidth || rowCount>= boardHeight)
			return 0;
		else {
				if (mineSweeperBoardButtons[rowCount][colCount].getIcon() != null) {
					return 1;
				}
				else
				{
					return 0;
				}	
		}
	}

	/**
	 * placing mines on the board
	 * @param sizeBoardTypeRow
	 * @param sizeBoardTypeCol
	 * @param mines
	 */
	static void setMinesOnBoard(int sizeBoardTypeRow, int sizeBoardTypeCol, int mines) {

		Random rm = new Random();

		while (temp < mines) {
			int row = rm.nextInt(sizeBoardTypeRow);
			int col = rm.nextInt(sizeBoardTypeCol);

			
			if (mineSweeperBoardButtons[row][col].getIcon() != null) {
				continue;
			} else {

				mineSweeperBoardButtons[row][col].setIcon(iconTwo);
				temp++;

			}
		}
	}

}