package gui;

import main.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/* The JFrame that showing various information
 * User can scroll through information using the arrow buttons. */
public class TutorialGUI extends JFrame {

	private JPanel pnTutorial;
	private JButton btnBack;

	private JLabel lblTip;

	private JButton btnNext;
	private JButton btnPrevious;
	private JLabel lblPage;

	private int tipPage; // Information the current user is viewing
	private final int totalTipPage = 4; // Total number of information

	/**
	 * Create the frame.
	 */
	public TutorialGUI() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TutorialGUI.class.getResource("/ImageFile/Star.png")));
		setTitle("Shooting Star");
		setResizable(false); // Prevent window resizing
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 100, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setVisible(true);

		pnTutorial = new JPanel() {
			/* Method that draws the background of pnTutorial */
			public void paintComponent(Graphics g) {
				Image startBackground = Toolkit.getDefaultToolkit()
						.getImage(TutorialGUI.class.getResource("/ImageFile/Background/TutorialBackground.jpg"));
				g.drawImage(startBackground, 0, 0, this.getWidth(), this.getHeight(), this);
			}
		};
		pnTutorial.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(pnTutorial);
		pnTutorial.setLayout(null);

		btnPrevious = new JButton();
		btnPrevious.setIcon(new ImageIcon(TutorialGUI.class.getResource("/ImageFile/LeftArrow.png")));
		btnPrevious.setBounds(340, 593, 45, 60);
		btnPrevious.setBorderPainted(false); // Remove the border of the button.
		btnPrevious.setContentAreaFilled(false); // Remove the background of the button.
		pnTutorial.add(btnPrevious);

		btnNext = new JButton();
		btnNext.setIcon(new ImageIcon(TutorialGUI.class.getResource("/ImageFile/RightArrow.png")));
		btnNext.setBounds(462, 593, 45, 60);
		btnNext.setBorderPainted(false); // Remove the border of the button.
		btnNext.setContentAreaFilled(false); // Remove the background of the button.
		pnTutorial.add(btnNext);

		lblPage = new JLabel();
		lblPage.setHorizontalAlignment(SwingConstants.CENTER);
		lblPage.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
		lblPage.setBounds(393, 589, 57, 69);
		pnTutorial.add(lblPage);

		btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/* When user press the BACK button, Turn off the current screen and open MenuGUI.*/
				new MenuGUI();
				dispose();
			}
		});
		btnBack.setBackground(SystemColor.inactiveCaptionBorder);
		btnBack.setFont(new Font("Dialog", Font.BOLD, 35));
		btnBack.setBounds(409, 684, 141, 52);
		btnBack.setFocusPainted(false);
		pnTutorial.add(btnBack);

		lblTip = new JLabel();
		lblTip.setBounds(0, 0, 584, 761);
		Image image = new ImageIcon(
				TutorialGUI.class.getResource("/ImageFile/Tip/Tip1.png")).getImage();
		// Match the image to the label size.
		image = image.getScaledInstance(lblTip.getWidth(), lblTip.getHeight(), Image.SCALE_SMOOTH);
		lblTip.setIcon(new ImageIcon(image));
		pnTutorial.add(lblTip);

		tipPage = 1; // current tip page
		lblPage.setText(String.format("%d/%d", tipPage, totalTipPage)); // Shows the current page number out of all pages.

		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/* When the < button is pressed, the previous tipPage is displayed. */
				if (tipPage != 1) {
					tipPage--;
					Image image = new ImageIcon(
							TutorialGUI.class.getResource(String.format("/ImageFile/Tip/Tip%d.png", tipPage))).getImage();
					// Match the image to the label size.
					image = image.getScaledInstance(lblTip.getWidth(), lblTip.getHeight(), Image.SCALE_SMOOTH);
					lblTip.setIcon(new ImageIcon(image));
					lblPage.setText(String.format("%d/%d", tipPage, totalTipPage));
				} // end if
			}
		}); // end btnPrevious ActionListener
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/* When the > button is pressed, the next tipPage is displayed. */
				if (tipPage != totalTipPage) {
					tipPage++;
					Image image = new ImageIcon(
							TutorialGUI.class.getResource(String.format("/ImageFile/Tip/Tip%d.png", tipPage))).getImage();
					// Match the image to the label size.
					image = image.getScaledInstance(lblTip.getWidth(), lblTip.getHeight(), Image.SCALE_SMOOTH);
					lblTip.setIcon(new ImageIcon(image));
					lblPage.setText(String.format("%d/%d", tipPage, totalTipPage));
				} // end if
			}
		}); // end btnNext ActionListener
	} // end Constructor method
} // end class TutorialGUI
