package gui;

import main.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/* The JFrame that main menu.
 * Users can choose whether to view tutorials, learn games, or view rankings. 
 * (Game difficulty can also be determined) */
public class MenuGUI extends JFrame {
	/* Global variables for game's difficulty */
	public static final int EASY = 1;
	public static final int HARD = 2;
	
	private JPanel pnMenu;

	private JButton btnTutorial;
	private JButton btnEasy;
	private JButton btnHard;
	private JButton btnRanking;
	private JButton btnBack;
	
	/**
	 * Create the frame.
	 */
	public MenuGUI() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuGUI.class.getResource("/ImageFile/Star.png")));
		setTitle("Shooting Star");
		setResizable(false); // Prevent window resizing
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 100, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setVisible(true);

		pnMenu = new JPanel() {
			/* Method that draws the background of pnMenu */
			public void paintComponent(Graphics g) {
				Image startBackground = Toolkit.getDefaultToolkit()
						.getImage(MenuGUI.class.getResource("/ImageFile/Background/MenuBackground.jpg"));
				g.drawImage(startBackground, 0, 0, this.getWidth(), this.getHeight(), this);
			}
		};
		pnMenu.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pnMenu);
		pnMenu.setLayout(null);

		btnTutorial = new JButton("Tutorial");
		btnTutorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/* When user press the Tutorial button, Turn off the current screen and open TutorialGUI.*/
				dispose();
				new TutorialGUI();
			}
		});
		btnTutorial.setFont(new Font("Arial Black", Font.BOLD, 35));
		btnTutorial.setBackground(new Color(224, 255, 255));
		btnTutorial.setBounds(180, 100, 224, 100);
		btnTutorial.setFocusPainted(false);
		pnMenu.add(btnTutorial);

		btnEasy = new JButton("Easy");
		btnEasy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/* When user press the Easy button, Turn off the current screen and Run GameGUI using Multithreading.
				 * At this time, pass the difficulty argument to EASY(=1). */
				dispose();
				Thread gameThread = new Thread(new GameGUI(EASY));
				gameThread.start();
			}
		});
		btnEasy.setBackground(new Color(102, 204, 255));
		btnEasy.setFont(new Font("Arial Black", Font.BOLD, 40));
		btnEasy.setBounds(180, 250, 224, 100);
		btnEasy.setFocusPainted(false);
		pnMenu.add(btnEasy);

		btnHard = new JButton("Hard");
		btnHard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/* When user press the Hard button, Turn off the current screen and Run GameGUI using Multithreading.
				 * At this time, pass the difficulty argument to HARD(=2). */
				dispose();
				Thread gameThread = new Thread(new GameGUI(HARD));
				gameThread.start();
			}
		});
		btnHard.setBackground(new Color(255, 51, 0));
		btnHard.setFont(new Font("Arial Black", Font.BOLD, 40));
		btnHard.setBounds(180, 400, 224, 100);
		btnHard.setFocusPainted(false);
		pnMenu.add(btnHard);

		btnRanking = new JButton("Ranking");
		btnRanking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/* When user press the Ranking button, Turn off the current screen and open RankingGUI.*/
				dispose();
				new RankingGUI();
			}
		});
		btnRanking.setBackground(new Color(255, 255, 240));
		btnRanking.setFont(new Font("Arial Black", Font.BOLD, 35));
		btnRanking.setBounds(180, 550, 224, 100);
		btnRanking.setFocusPainted(false);
		pnMenu.add(btnRanking);

		btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/* When user press the BACK button, Turn off the current screen and open StartGUI.*/
				dispose();
				new StartGUI();
			}
		});
		btnBack.setBackground(SystemColor.inactiveCaptionBorder);
		btnBack.setFont(new Font("Dialog", Font.BOLD, 35));
		btnBack.setBounds(409, 684, 141, 52);
		btnBack.setFocusPainted(false);
		pnMenu.add(btnBack);
		
		pnMenu.repaint(); // Repaint pnMenu. 
		// (If this line is not executed, sometimes the button is not visible when coming from another GUI.)
	} // end constructor method
} // end class MenuGUI
