package dialog;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;

/* The Jdialog that appears after the user level up. 
 * The user can choose which stats to up (ATK, DEX, SPEED) */
public class StatDialog extends JDialog {
	/* Global and instance variables to return whether the user wants to up ATK, DEX, or SPEED */
	public static final int ATK = 1;
	public static final int DEX = 2;
	public static final int SPEED = 3;
	private int stat;
	
	private final JPanel pnQuestion = new JPanel();

	/**
	 * Create the dialog.
	 */
	public StatDialog(JFrame parent) {
		super(parent, true); // The parent argument contains the JFrame of the running GameGUI.
		getContentPane().setBackground(Color.DARK_GRAY);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(StatDialog.class.getResource("/ImageFile/Star.png")));
		this.setTitle("Level Up");
		this.setResizable(false); // Prevent window resizing
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE); // Prevent windows from being closed with the X button
		this.setSize(new Dimension(380, 200));
		this.setLocationRelativeTo(parent); // Place it in the center of the parent Frame.

		getContentPane().setLayout(new GridLayout(2, 1, 0, 0));
		pnQuestion.setBackground(Color.DARK_GRAY);
		pnQuestion.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(pnQuestion);
		pnQuestion.setLayout(new BorderLayout(0, 0));
		{ /* Top of ContentPane */
			// Used html language to write spaces in JLabel.
			JLabel lblQuestion = new JLabel(
					"<html><center>! Level UP !<br>Which stat do you want to up?</center><html>");
			lblQuestion.setHorizontalAlignment(SwingConstants.CENTER);
			lblQuestion.setForeground(Color.WHITE);
			lblQuestion.setFont(new Font("Comic Sans MS", Font.BOLD, 21));
			pnQuestion.add(lblQuestion);
		}
		{ /* Bottom of ContentPane */
			JPanel pnButton = new JPanel();
			pnButton.setBackground(Color.DARK_GRAY);
			getContentPane().add(pnButton);
			pnButton.setLayout(null);
			{
				JButton btnAtk = new JButton("ATK");
				btnAtk.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						/* Case of user wants ATK to rise */
						stat = ATK;
						dispose();
					}
				});
				btnAtk.setBackground(new Color(255, 240, 245));
				btnAtk.setBounds(20, 10, 90, 50);
				btnAtk.setFont(new Font("Arial", Font.BOLD, 20));
				btnAtk.setFocusPainted(false);
				pnButton.add(btnAtk);
			}
			{
				JButton btnDex = new JButton("DEX");
				btnDex.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						/* Case of user wants DEX to rise */
						stat = DEX;
						dispose();
					}
				});
				btnDex.setBackground(new Color(255, 250, 205));
				btnDex.setBounds(130, 10, 90, 50);
				btnDex.setFont(new Font("Arial", Font.BOLD, 20));
				btnDex.setFocusPainted(false);
				pnButton.add(btnDex);
			}
			{
				JButton btnSpeed = new JButton("SPEED");
				btnSpeed.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						/* Case of user wants SPEED to rise */
						stat = SPEED;
						dispose();
					}
				});
				btnSpeed.setBackground(new Color(204, 255, 204));
				btnSpeed.setBounds(240, 10, 105, 50);
				btnSpeed.setFont(new Font("Arial", Font.BOLD, 20));
				btnSpeed.setFocusPainted(false);
				pnButton.add(btnSpeed);
			}
		}
	} // end constructor method

	/* A Method that displays StatDialog and returns the button selected by the user */
	public int showStatDialog() {
		this.setVisible(true);
		return this.stat;
	} // end method showStatDialog
} // end class StatDialog
