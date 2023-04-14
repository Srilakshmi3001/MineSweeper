/**
 * @author [Lakshmi Pinnika]
 * @email [lakshmipinnika1999@gmail.com]
 * @create date 2022-11-13 22:30:07
 * @modify date 2022-11-13 22:30:07
 * @desc [Implement in Java with a GUI the game Minesweeper ]
 */
/*
 Name :Lakshmi Pinnika
Course : Advanced programming concepts
Section :031
Student Id :999902604
*/
// required package importing
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

// declaration of class name as Player which extends another class
public class Player extends FreeMineSweeperGame{

	/**
	 * The method will runs the entire game by finding the mines and 
	 * triggering the action events
	 * @param sizeBoardTypeRow
	 * @param sizeBoardTypeCol
	 */
	static void mineCount(int sizeBoardTypeRow, int sizeBoardTypeCol) {
		for (int rowCount = 0; rowCount < sizeBoardTypeRow; rowCount++) {
			for (int colCount = 0; colCount < sizeBoardTypeCol; colCount++) {
				if (mineSweeperBoardButtons[rowCount][colCount].getIcon() != null) {

				} else {
					int value =   numberOfMines(rowCount, colCount - 1) 							    
								+ numberOfMines(rowCount + 1, colCount)
								+ numberOfMines(rowCount - 1, colCount)
								+ numberOfMines(rowCount, colCount + 1) 
								+ numberOfMines(rowCount + 1, colCount + 1)
								+ numberOfMines(rowCount + 1, colCount - 1)								
								+ numberOfMines(rowCount - 1, colCount + 1)								 
								+ numberOfMines(rowCount - 1, colCount - 1);
					if(value==0)
					{
						mineSweeperBoardButtons[rowCount][colCount].setText(" ");
					}
					else
						mineSweeperBoardButtons[rowCount][colCount].setText(Integer.toString(value));
				}
			}
		}
		for (int rowCount = 0; rowCount < sizeBoardTypeRow; rowCount++) {
			final int rowLoc = rowCount;
			for (int colCount = 0; colCount < sizeBoardTypeCol; colCount++) {

				final int colLoc = colCount;
				mineSweeperBoardButtons[rowLoc][colLoc].addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {

						if (mineSweeperBoardButtons[rowLoc][colLoc].getIcon() != null) {
							if (mineSweeperBoardButtons[rowLoc][colLoc].getIcon().equals(iconTwo) && mineSweeperBoardButtons[rowLoc][colLoc].getText().isEmpty()) {
								ImageIcon blast = new ImageIcon("mine1.png");
								
								mineSweeperBoardButtons[rowLoc][colLoc].setIcon(blast);
								gameOver();
							}

						}
						if (!(mineSweeperBoardButtons[rowLoc][colLoc].getText().isEmpty())) {
							mineSweeperBoardButtons[rowLoc][colLoc].setIcon(null);
						}
						String val = mineSweeperBoardButtons[rowLoc][colLoc].getText();
						if (val.equals(" ") && mineSweeperBoardButtons[rowLoc][colLoc].getBackground().equals(Color.WHITE))
							checkNighbourCell(rowLoc,colLoc);
						
						mineSweeperBoardButtons[rowLoc][colLoc].setBackground(Color.WHITE);
						mineSweeperBoardButtons[rowLoc][colLoc].setForeground(Color.BLACK);
						gameWin();

					}
					private void checkNighbourCell(int rowCount, int colCount) {
						
						emptyCell(rowCount, colCount + 1);
						emptyCell(rowCount, colCount - 1);
						emptyCell(rowCount + 1, colCount);
						emptyCell(rowCount - 1, colCount);
						emptyCell(rowCount - 1, colCount - 1);
						emptyCell(rowCount + 1, colCount + 1);
						emptyCell(rowCount - 1, colCount + 1);						
						emptyCell(rowCount + 1, colCount - 1);
						
						

					}

					/**
					 * open the empty cells
					 * @param rowCount
					 * @param colCount
					 */
					private void emptyCell(int rowCount, int colCount) {
					
						if (rowCount < 0 || colCount<0 || colCount>=sizeBoardTypeCol || rowCount>=sizeBoardTypeRow)
							return ;
						else {
							try {
								String val = mineSweeperBoardButtons[rowCount][colCount].getText();
								if (val.equals(" ") && !(mineSweeperBoardButtons[rowCount][colCount].getBackground().equals(Color.WHITE))) {

									mineSweeperBoardButtons[rowCount][colCount].setBackground(Color.WHITE);
									mineSweeperBoardButtons[rowCount][colCount].setForeground(Color.WHITE);
									mineSweeperBoardButtons[rowCount][colCount].setIcon(null);
									checkNighbourCell(rowCount, colCount);

								}
							} catch (Exception e) {
								return ;
							}
						}
		
					}

				});

				mineSweeperBoardButtons[rowLoc][colLoc].addMouseListener(new MouseAdapter() {

					public void mouseClicked(MouseEvent event) {

						if (event.getButton() == 3) {
							
							if (mineSweeperBoardButtons[rowLoc][colLoc].getIcon() != null) {
								if (mineSweeperBoardButtons[rowLoc][colLoc].getIcon().equals(rightClick)
										|| !(mineSweeperBoardButtons[rowLoc][colLoc].getText().isEmpty())) {
									mineSweeperBoardButtons[rowLoc][colLoc].setIcon(null);
								} else if (mineSweeperBoardButtons[rowLoc][colLoc].getIcon().equals(iconTwo)) {
									mineSweeperBoardButtons[rowLoc][colLoc].setIcon(rightClick);
								} else {
									mineSweeperBoardButtons[rowLoc][colLoc].setIcon(iconTwo);
								}
							} else {
								if (!(mineSweeperBoardButtons[rowLoc][colLoc].getBackground().equals(Color.WHITE)))
									mineSweeperBoardButtons[rowLoc][colLoc].setIcon(rightClick);
							}

						}
					}
				});
			}
		}
	}
}
