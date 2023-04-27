package dialog;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/* The Jdialog that appears after the user presses the P key to stop the game. 
 * The user can choose to resume the game, go to the menu, or quit the game. */
public class PauseDialog extends JDialog {
	/* Global and instance variables to return whether the user resumes the game, goes to the menu, or quits the game. */
	public static final int RESUME = 1;
	public static final int MENU = 2;
	public static final int QUIT = 3;
	private int clickOption;
	
	private final JPanel pnPause = new JPanel();

	/**
	 * Create the dialog.
	 */
	public PauseDialog(JFrame parent) {
		super(parent, true); // The parent argument contains the JFrame of the running GameGUI.
		getContentPane().setBackground(Color.DARK_GRAY);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(PauseDialog.class.getResource("/ImageFile/Star.png")));
		this.setTitle("Pause");
		this.setResizable(false); // Prevent window resizing
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE); // Prevent windows from being closed with the X button
		this.setSize(new Dimension(200, 300));
		this.setLocationRelativeTo(parent); // Place it in the center of the parent Frame.
		
		getContentPane().setLayout(null);
		pnPause.setBounds(0, 0, 184, 60);
		pnPause.setBackground(Color.DARK_GRAY);
		pnPause.setBorder(new EmptyBorder(5, 5, 5, 5));
		pnPause.setLayout(new BorderLayout(0, 0));
		getContentPane().add(pnPause);
		{
			JLabel lblPause = new JLabel("Pause");
			lblPause.setHorizontalAlignment(SwingConstants.CENTER);
			lblPause.setForeground(Color.WHITE);
			lblPause.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
			pnPause.add(lblPause);
		}
		{
			JPanel pnButton = new JPanel();
			pnButton.setBackground(Color.DARK_GRAY);
			pnButton.setBounds(0, 66, 184, 195);
			pnButton.setLayout(null);
			getContentPane().add(pnButton);
			{
				JButton btnResume = new JButton("Resume");
				btnResume.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						/* Case of user wants to resume the game and presses the Resume button */
						clickOption = RESUME;
						dispose();
					}
				});
				btnResume.setBackground(new Color(204, 255, 153));
				btnResume.setBounds(40, 10, 104, 40);
				btnResume.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
				btnResume.setFocusPainted(false);
				pnButton.add(btnResume);
			}
			{
				JButton btnMenu = new JButton("Menu");
				btnMenu.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						/* Case of user wants to go to the menu and presses the Menu button */
						clickOption = MENU;
						dispose();
					}
				});
				btnMenu.setBackground(new Color(255, 222, 173));
				btnMenu.setBounds(40, 70, 104, 40);
				btnMenu.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
				btnMenu.setFocusPainted(false);
				pnButton.add(btnMenu);
			}
			{
				JButton btnQuit = new JButton("Quit");
				btnQuit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						/* Case of user wants to quit the game and presses the No button */
						clickOption = QUIT;
						dispose();
					}
				});
				btnQuit.setBackground(new Color(255, 192, 203));
				btnQuit.setBounds(40, 130, 104, 40);
				btnQuit.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
				btnQuit.setFocusPainted(false);
				pnButton.add(btnQuit);
			}
		}
	} // end constructor method
	
	/* A Method that displays PauseDialog and returns the button selected by the user */
	public int showPauseDialog() {
		this.setVisible(true);
		return this.clickOption;
	} // end method showPauseDialog
} // end class PauseDialog
