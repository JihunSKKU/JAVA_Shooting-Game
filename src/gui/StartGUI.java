package gui;

import main.*;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/* The JFrame that startGUI. 
 * User can decide whether to start or quit the game. */
public class StartGUI extends JFrame {

	private JPanel pnStartScreen;

	private JLabel lblShootingStar;
	private JButton btnStart;
	private JButton btnQuit;

	/**
	 * Create the frame.
	 */
	public StartGUI() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(StartGUI.class.getResource("/ImageFile/Star.png")));
		setTitle("Shooting Star");
		setResizable(false); // Prevent window resizing
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 100, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setVisible(true);

		pnStartScreen = new JPanel() {
			public void paintComponent(Graphics g) {
				/* Method that draws the background of pnStartScreen */
				Image startBackground = Toolkit.getDefaultToolkit()
						.getImage(StartGUI.class.getResource("/ImageFile/Background/StartBackground.jpg"));
				g.drawImage(startBackground, 0, 0, this.getWidth(), this.getHeight(), this);
			}
		};
		pnStartScreen.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(pnStartScreen);
		pnStartScreen.setLayout(null);

		// Used html language to write spaces in JLabel.
		lblShootingStar = new JLabel("<html><center>Shooting<br>Star</center><html>");
		lblShootingStar.setForeground(new Color(255, 255, 255));
		lblShootingStar.setHorizontalAlignment(SwingConstants.CENTER);
		lblShootingStar.setFont(new Font("Arial Black", Font.BOLD, 60));
		lblShootingStar.setBounds(12, 161, 560, 162);
		pnStartScreen.add(lblShootingStar);

		btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/* When user press the Start button, Turn off the current screen and open MenuGUI.*/
				dispose();
				new MenuGUI();
			}
		});
		btnStart.setBackground(new Color(102, 204, 255));
		btnStart.setFont(new Font("Arial Black", Font.BOLD, 35));
		btnStart.setBounds(190, 356, 200, 72);
		btnStart.setFocusPainted(false);
		pnStartScreen.add(btnStart);

		btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/* When user press the Quit button, exit the program. */
				System.exit(0);
			}
		});
		btnQuit.setBackground(new Color(255, 51, 102));
		btnQuit.setFont(new Font("Arial Black", Font.BOLD, 40));
		btnQuit.setBounds(190, 458, 200, 72);
		btnQuit.setFocusPainted(false);
		pnStartScreen.add(btnQuit);
	} // end constructor method
} // end class StartGUI
