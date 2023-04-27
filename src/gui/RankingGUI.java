package gui;

import main.*;
import object.Record;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.ImageIcon;

/* The JFrame that RankingGUI.
 * User can see the ranking for each level of difficulty. 
 * Name, score and time are displayed. */
public class RankingGUI extends JFrame {

	private JPanel pnRanking;
	private JButton btnBack;
	private JLabel lblRanking;
	
	private JLabel lblEasyMode;
	private JLabel lblEasyGoldMedal;
	private JLabel lblEasySilverMedal;
	private JLabel lblEasyBronzeMedal;
	
	private JLabel lblHardMode;
	private JLabel lblHardGoldMedal;
	private JLabel lblHardSilverMedal;
	private JLabel lblHardBronzeMedal;

	private String info; // Variable that stores the ranking in the form of a string according to the given format.

	/**
	 * Create the frame.
	 */
	public RankingGUI() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuGUI.class.getResource("/ImageFile/Star.png")));
		setTitle("Shooting Star");
		setResizable(false); // Prevent window resizing
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 100, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setVisible(true);

		pnRanking = new JPanel() {
			public void paintComponent(Graphics g) {
				/* Method that draws the background of pnRanking */
				Image startBackground = Toolkit.getDefaultToolkit().getImage(
						TutorialGUI.class.getResource("/ImageFile/Background/RankingBackground.jpg"));
				g.drawImage(startBackground, 0, 0, this.getWidth(), this.getHeight(), this);
			}
		};
		pnRanking.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pnRanking);

		btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/* When user press the BACK button, Turn off the current screen and open MenuGUI.*/
				dispose();
				new MenuGUI();
			}
		});
		pnRanking.setLayout(null);
		btnBack.setBackground(SystemColor.inactiveCaptionBorder);
		btnBack.setFont(new Font("Dialog", Font.BOLD, 35));
		btnBack.setBounds(409, 684, 141, 52);
		btnBack.setFocusPainted(false);
		pnRanking.add(btnBack);

		lblRanking = new JLabel("Ranking");
		lblRanking.setForeground(Color.WHITE);
		lblRanking.setHorizontalAlignment(SwingConstants.CENTER);
		lblRanking.setFont(new Font("Arial Black", Font.BOLD, 50));
		lblRanking.setBounds(12, 10, 560, 74);
		pnRanking.add(lblRanking);

		/* Lines to load easyRecord text File */
		ArrayList<Record> easyRecord = new ArrayList<>();
		try { /* Exception Handling */
			FileInputStream file = new FileInputStream("easyRecord.txt");
			Scanner buffer = new Scanner(file);

			/* Load all lines into easyRecord. (line by line) */
			while (buffer.hasNext()) {
				String line = buffer.nextLine();
				String tokens[] = line.split(" / "); // When saving, each information is separated by '/'.

				String name = tokens[0];
				int score = Integer.parseInt(tokens[1]); // Store the score in integer format.
				// Split time into ":" and save in integer format.
				String[] time = tokens[2].split(":"); 
				int minute = Integer.parseInt(time[0]);
				int second = Integer.parseInt(time[1]);

				// After creating a Record Object with the information received above, add it to the arrayList.
				easyRecord.add(new Record(name, score, minute, second));
			} // end while

			file.close();
			buffer.close();

			// Sort easyRecord arrayList. (The sorting standard is the compareTo method of the Record object)
			Collections.sort(easyRecord);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		lblEasyMode = new JLabel("Easy mode");
		lblEasyMode.setForeground(Color.GREEN);
		lblEasyMode.setFont(new Font("Arial Black", Font.BOLD, 30));
		lblEasyMode.setBounds(40, 80, 243, 52);
		pnRanking.add(lblEasyMode);

		/* Save the first place record in info according to the format. */
		if (easyRecord.size() >= 1) {
			info = String.format("<html>&nbsp; %s<br>&nbsp; Score : %d &nbsp; Time : %d:%02d<html>",
					easyRecord.get(0).getName(), easyRecord.get(0).getScore(), easyRecord.get(0).getMinute(),
					easyRecord.get(0).getSecond());
		} else { // If not present, save as " None".
			info = "  None";
		}
		lblEasyGoldMedal = new JLabel(info);
		lblEasyGoldMedal.setIcon(new ImageIcon(RankingGUI.class.getResource("/ImageFile/Medal/GoldMedal.png")));
		lblEasyGoldMedal.setForeground(Color.WHITE);
		lblEasyGoldMedal.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblEasyGoldMedal.setBounds(40, 140, 510, 60);
		pnRanking.add(lblEasyGoldMedal);

		/* Save the second place record in info according to the format. */
		if (easyRecord.size() >= 2) {
			info = String.format("<html>&nbsp; %s<br>&nbsp; Score : %d &nbsp; Time : %d:%02d<html>",
					easyRecord.get(1).getName(), easyRecord.get(1).getScore(), easyRecord.get(1).getMinute(),
					easyRecord.get(1).getSecond());
		} else { // If not present, save as " None".
			info = "  None";
		}
		lblEasySilverMedal = new JLabel(info);
		lblEasySilverMedal.setIcon(new ImageIcon(RankingGUI.class.getResource("/ImageFile/Medal/SiverMedal.png")));
		lblEasySilverMedal.setForeground(Color.WHITE);
		lblEasySilverMedal.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblEasySilverMedal.setBounds(40, 220, 510, 60);
		pnRanking.add(lblEasySilverMedal);

		/* Save the third place record in info according to the format. */
		if (easyRecord.size() >= 3) {
			info = String.format("<html>&nbsp; %s<br>&nbsp; Score : %d &nbsp; Time : %d:%02d<html>",
					easyRecord.get(2).getName(), easyRecord.get(2).getScore(), easyRecord.get(2).getMinute(),
					easyRecord.get(2).getSecond());
		} else { // If not present, save as " None".
			info = "  None";
		}
		lblEasyBronzeMedal = new JLabel(info);
		lblEasyBronzeMedal.setIcon(new ImageIcon(RankingGUI.class.getResource("/ImageFile/Medal/BronzeMedal.png")));
		lblEasyBronzeMedal.setForeground(Color.WHITE);
		lblEasyBronzeMedal.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblEasyBronzeMedal.setBounds(40, 300, 510, 60);
		pnRanking.add(lblEasyBronzeMedal);

		
		/* lines to load hardRecord text File */
		ArrayList<Record> hardRecord = new ArrayList<>();
		try { /* Exception Handling */
			FileInputStream file = new FileInputStream("hardRecord.txt");
			Scanner buffer = new Scanner(file);

			/* Load all lines into hardRecord. (line by line) */
			while (buffer.hasNext()) {
				String line = buffer.nextLine();
				String tokens[] = line.split(" / "); // When saving, each information is separated by '/'.

				String name = tokens[0];
				int score = Integer.parseInt(tokens[1]); // Store the score in integer format.
				// Split time into ":" and save in integer format.
				String[] time = tokens[2].split(":");
				int minute = Integer.parseInt(time[0]);
				int second = Integer.parseInt(time[1]);
 
				// After creating a Record Object with the information received above, add it to the arrayList.
				hardRecord.add(new Record(name, score, minute, second));
			} // end while

			file.close();
			buffer.close();

			// Sort hardRecord arrayList. (The sorting standard is the compareTo method of the Record object)
			Collections.sort(hardRecord); 
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		lblHardMode = new JLabel("Hard mode");
		lblHardMode.setForeground(Color.RED);
		lblHardMode.setFont(new Font("Arial Black", Font.BOLD, 30));
		lblHardMode.setBounds(40, 380, 243, 52);
		pnRanking.add(lblHardMode);

		/* Save the first place record in info according to the format. */
		if (hardRecord.size() >= 1) {
			info = String.format("<html>&nbsp; %s<br>&nbsp; Score : %d &nbsp; Time : %d:%02d<html>",
					hardRecord.get(0).getName(), hardRecord.get(0).getScore(), hardRecord.get(0).getMinute(),
					hardRecord.get(0).getSecond());
		} else { // If not present, save as " None".
			info = "  None";
		}
		lblHardGoldMedal = new JLabel(info);
		lblHardGoldMedal.setIcon(new ImageIcon(RankingGUI.class.getResource("/ImageFile/Medal/GoldMedal.png")));
		lblHardGoldMedal.setForeground(Color.WHITE);
		lblHardGoldMedal.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblHardGoldMedal.setBounds(40, 440, 510, 60);
		pnRanking.add(lblHardGoldMedal);

		/* Save the second place record in info according to the format. */
		if (hardRecord.size() >= 2) {
			info = String.format("<html>&nbsp; %s<br>&nbsp; Score : %d &nbsp; Time : %d:%02d<html>",
					hardRecord.get(1).getName(), hardRecord.get(1).getScore(), hardRecord.get(1).getMinute(),
					hardRecord.get(1).getSecond());
		} else { // If not present, save as " None".
			info = "  None";
		}
		lblHardSilverMedal = new JLabel(info);
		lblHardSilverMedal.setIcon(new ImageIcon(RankingGUI.class.getResource("/ImageFile/Medal/SiverMedal.png")));
		lblHardSilverMedal.setForeground(Color.WHITE);
		lblHardSilverMedal.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblHardSilverMedal.setBounds(40, 520, 510, 60);
		pnRanking.add(lblHardSilverMedal);

		/* Save the third place record in info according to the format. */
		if (hardRecord.size() >= 3) {
			info = String.format("<html>&nbsp; %s<br>&nbsp; Score : %d &nbsp; Time : %d:%02d<html>",
					hardRecord.get(2).getName(), hardRecord.get(2).getScore(), hardRecord.get(2).getMinute(),
					hardRecord.get(2).getSecond());
		} else { // If not present, save as " None".
			info = "  None";
		}
		lblHardBronzeMedal = new JLabel(info);
		lblHardBronzeMedal.setIcon(new ImageIcon(RankingGUI.class.getResource("/ImageFile/Medal/BronzeMedal.png")));
		lblHardBronzeMedal.setForeground(Color.WHITE);
		lblHardBronzeMedal.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblHardBronzeMedal.setBounds(40, 600, 510, 60);
		pnRanking.add(lblHardBronzeMedal);
	} // end constructor method
} // end class RankingGUI
