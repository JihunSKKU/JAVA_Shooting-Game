package dialog;

import gui.*;
import exception.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

/* A Jdialog that appears after dying in the game.
 * Play time and score are displayed, and the user's name can be recorded. 
 * It also asks the user if they want to restart the game.*/
public class GameOverDialog extends JDialog {
	/* Global and instance variables to return whether the user restarts the game or goes to the menu */
	public static final int YES = 1;
	public static final int NO = 0;
	private int clickOption;

	private final JPanel pnInformation = new JPanel();
	private JLabel lblText;
	private JTextField textFieldName;
	private JButton btnOK;

	/**
	 * Create the dialog.
	 */
	public GameOverDialog(JFrame parent, int playTime, int score, int difficulty) {
		super(parent, true); // The parent argument contains the JFrame of the running GameGUI.
		getContentPane().setBackground(Color.DARK_GRAY);
		this.setIconImage(
				Toolkit.getDefaultToolkit().getImage(GameOverDialog.class.getResource("/ImageFile/Star.png")));
		this.setTitle("Game Over");
		this.setResizable(false); // Prevent window resizing
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE); // Prevent windows from being closed with the X button
		this.setSize(new Dimension(399, 345));
		this.setLocationRelativeTo(parent); // Place it in the center of the parent Frame.

		getContentPane().setLayout(new BorderLayout());
		pnInformation.setBackground(Color.WHITE);
		pnInformation.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(pnInformation, BorderLayout.CENTER);
		pnInformation.setLayout(new BorderLayout(0, 0));
		{ /* North of pnInformation */
			JLabel lblGameOver = new JLabel("Game Over");
			lblGameOver.setHorizontalAlignment(SwingConstants.CENTER);
			lblGameOver.setForeground(Color.RED);
			lblGameOver.setFont(new Font("Arial Black", Font.BOLD, 30));
			pnInformation.add(lblGameOver, BorderLayout.NORTH);
		}
		{ /* South of pnInformation */
			JLabel lblQuesition = new JLabel("Do you want to try again?");
			lblQuesition.setForeground(new Color(0, 0, 0));
			lblQuesition.setHorizontalAlignment(SwingConstants.CENTER);
			lblQuesition.setFont(new Font("Arial Black", Font.BOLD, 20));
			pnInformation.add(lblQuesition, BorderLayout.SOUTH);
		}
		{ /* Center of pnInformation */
			JPanel panel = new JPanel();
			panel.setBackground(Color.WHITE);
			pnInformation.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			{
				// Used html language to write spaces in JLabel.
				String info = String.format("<html><center>Play Time %d:%02d<br>Score %d</center><html>", playTime / 60,
						playTime % 60, score);
				JLabel lblInformation = new JLabel(info);
				lblInformation.setBounds(12, 10, 349, 58);
				lblInformation.setHorizontalAlignment(SwingConstants.CENTER);
				lblInformation.setFont(new Font("Arial Black", Font.BOLD, 20));
				panel.add(lblInformation);
			}

			// Used html language to write spaces in JLabel.
			lblText = new JLabel("<html>If you want to record,<br>Input your name and click 'OK'.<html>");
			lblText.setFont(new Font("Arial Black", Font.BOLD, 17));
			lblText.setBounds(12, 62, 349, 60);
			panel.add(lblText);

			// Textfield for user to enter name
			textFieldName = new JTextField();
			textFieldName.setFont(new Font("Arial", Font.BOLD, 15));
			textFieldName.setHorizontalAlignment(SwingConstants.CENTER);
			textFieldName.setBounds(12, 132, 267, 34);
			panel.add(textFieldName);
			textFieldName.setColumns(10);

			btnOK = new JButton("OK");
			btnOK.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					/* When user press the OK button, first check that the textField is not empty. */
					if (!textFieldName.getText().isEmpty()) {
						try { /* Exception Handling */
							String name = textFieldName.getText();
							Pattern pattern = Pattern.compile("[^a-zA-Z0-9\\s]");
							if (pattern.matcher(name).find())
								/* If user enters a character that is not in the pattern above (ex. special character),
								 * throws an formatException. */
								throw new formatException();

							btnOK.setEnabled(false);  // Disable OK button
							
							// Open each file according to difficulty level.
							FileOutputStream file = null;
							if (difficulty == MenuGUI.EASY)
								file = new FileOutputStream("easyRecord.txt", true);
							else if (difficulty == MenuGUI.HARD)
								file = new FileOutputStream("hardRecord.txt", true);

							// Write the user's name, score, and play time to the file.
							PrintWriter buffer = new PrintWriter(file);
							String recordInfo = String.format("%s / %d / %d:%02d", name, score, playTime / 60,
									playTime % 60);
							buffer.println(recordInfo);

							buffer.close();
							file.close();
						} catch (formatException formatError) {
							/* Case of user enters special character, show the error message. */
							JOptionPane.showMessageDialog(GameOverDialog.this, "Special characters cannot be used.",
									"Name format Error", JOptionPane.ERROR_MESSAGE);
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				}
			});
			btnOK.setBackground(new Color(211, 211, 211));
			btnOK.setFont(new Font("Arial Black", Font.BOLD, 17));
			btnOK.setBounds(291, 132, 70, 34);
			btnOK.setFocusPainted(false);
			panel.add(btnOK);
		}
		{ /* South of ContentPane */
			JPanel pnButton = new JPanel();
			pnButton.setBackground(Color.WHITE);
			getContentPane().add(pnButton, BorderLayout.SOUTH);
			pnButton.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 5));
			{
				JButton btnYes = new JButton("Yes");
				btnYes.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						/* Case of user wants to restart the game and presses the Yes button */
						clickOption = YES;
						dispose();
					}
				});
				btnYes.setBackground(new Color(224, 255, 255));
				btnYes.setFont(new Font("Arial Black", Font.BOLD, 17));
				btnYes.setFocusPainted(false);
				pnButton.add(btnYes);
			}
			{
				JButton btnNo = new JButton("No");
				btnNo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						/* Case of user wants to quit the game and presses the No button (go to menu) */
						clickOption = NO;
						dispose();
					}
				});
				btnNo.setBackground(new Color(255, 192, 203));
				btnNo.setFont(new Font("Arial Black", Font.BOLD, 17));
				btnNo.setFocusPainted(false);
				pnButton.add(btnNo);
			}
		}
	} // end constructor method

	/* A Method that displays GameOverDialog and returns the button selected by the user */
	public int showGameOverDialog() {
		this.setVisible(true);
		return this.clickOption; // YES or NO
	} // end method showClearDialog
} // end class GameOverDialog
