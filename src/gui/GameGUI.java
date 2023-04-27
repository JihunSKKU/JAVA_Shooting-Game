package gui;

import main.*;
import object.*;
import dialog.*;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JProgressBar;
import javax.swing.ImageIcon;

/* The JFrame that GameGUI.
 * This class runs with multi-threading, so implemented Runnable.
 * And since we need to receive the user's key, also implemented KeyListener.
 * 
 * It can be seen as the core class in which the game of this project runs. */
public class GameGUI extends JFrame implements Runnable, KeyListener {

	private JPanel pnGame;

	private int score = 0; // Variable for current score
	private int levelUpScore = 50; // Variable for score required for next level up
	private double playTime = 0; // Variable for play time
	private int difficulty; // Variable for game's difficulty 
	// (It receives what the user selects in the MenuGUI and passes as an argument.)
	
	/* Variables that are true when the corresponding key is pressed and change to false when the key is released */
	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;

	private boolean start = true; // A variable that is true when the game should be run and false when it should be stopped
	private boolean gameOver = false; // A variable that is true only if the user is dead
	private boolean clear = false; // A variable that is true only if the user is clear the game
	private boolean goMenu = false; // A variable that is true only if the user click Menu button 

	// User controlled spaceship
	private User user = new User(); 
	private JLabel lblUser; // Labels are drawn with the variables that the above user object has.
	
	// ArrayList of stars fired by the user
	private ArrayList<Star> starList = null;
	private ArrayList<JLabel> lblStarList = null; // Labels are drawn with the variables that the above star object has.

	// ArrayList of Enemies
	private ArrayList<Enemy> enemyList = null;
	private ArrayList<JLabel> lblEnemyList = null; // Labels are drawn with the variables that the above enemy object has.

	private JLabel lblScore; // JLabel to display the current score
	private JLabel lblHP; // JLabel that just shows the "HP" String
	private JLabel lblPlayTime; // JLabel to display the current play time
	private JProgressBar progressBarHP; // JProgressBar showing user's current HP
	private JLabel lblLevel; // JLabel to display the current user's level
	private JLabel lblAtk; // JLabel to display the current user's ATK
	private JLabel lblDex; // JLabel to display the current user's DEX
	private JLabel lblSpeed; // JLabel to display the current user's SPEED
	private JLabel lblPause; // JLabel that just shows the "|| : Enter P" String

	/**
	 * Create the frame.
	 */
	public GameGUI(int difficulty) {
		this.difficulty = difficulty; // Stores the difficulty passed as an argument.

		// ArrayLists declared above
		starList = new ArrayList<Star>();
		lblStarList = new ArrayList<JLabel>();
		enemyList = new ArrayList<Enemy>();
		lblEnemyList = new ArrayList<JLabel>();

		setIconImage(Toolkit.getDefaultToolkit().getImage(GameGUI.class.getResource("/ImageFile/Star.png")));
		setTitle("Shooting Star");
		setResizable(false); // Prevent window resizing
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 100, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setVisible(true);

		pnGame = new JPanel() {
			public void paintComponent(Graphics g) {
				/* Method that draws the background of pnGame */
				Image startBackground = Toolkit.getDefaultToolkit().getImage(
						GameGUI.class.getResource("/ImageFile/Background/GameBackground.jpg"));
				g.drawImage(startBackground, 0, 0, this.getWidth(), this.getHeight(), this);
			}
		};
		pnGame.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pnGame);
		pnGame.setLayout(null);

		// Receives the user's current variable values and draws a JLabel.
		lblUser = new JLabel();
		lblUser.setBounds(user.getPositionX(), user.getPositionY(), user.getWidth(), user.getHeigth());
		lblUser.setIcon(user.getSpaceshipImage());
		pnGame.add(lblUser);

		/* Each Label declared above is drawn according to the format. */
		lblScore = new JLabel("Score " + score);
		lblScore.setForeground(Color.WHITE);
		lblScore.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
		lblScore.setBounds(27, 129, 208, 43);
		pnGame.add(lblScore);

		lblPlayTime = new JLabel("Time 0:00");
		lblPlayTime.setForeground(Color.WHITE);
		lblPlayTime.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
		lblPlayTime.setBounds(27, 76, 208, 43);
		pnGame.add(lblPlayTime);

		progressBarHP = new JProgressBar();
		progressBarHP.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		progressBarHP.setBackground(Color.WHITE);
		progressBarHP.setForeground(Color.RED);
		progressBarHP.setBounds(169, 711, 315, 40);
		progressBarHP.setValue(user.getCurrentHP());
		progressBarHP.setStringPainted(true); // Display the current progress percentage in the progressbar.
		pnGame.add(progressBarHP);

		lblHP = new JLabel("HP");
		lblHP.setForeground(Color.WHITE);
		lblHP.setHorizontalAlignment(SwingConstants.CENTER);
		lblHP.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		lblHP.setBounds(100, 711, 64, 40);
		pnGame.add(lblHP);

		lblLevel = new JLabel("Level : " + user.getLevel());
		lblLevel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLevel.setForeground(Color.WHITE);
		lblLevel.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		lblLevel.setBounds(361, 23, 200, 40);
		pnGame.add(lblLevel);

		lblAtk = new JLabel("ATK : " + user.getAtk());
		lblAtk.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAtk.setForeground(Color.WHITE);
		lblAtk.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		lblAtk.setBounds(361, 74, 200, 40);
		pnGame.add(lblAtk);

		lblDex = new JLabel("DEX : " + user.getDex());
		lblDex.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDex.setForeground(Color.WHITE);
		lblDex.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		lblDex.setBounds(361, 124, 200, 40);
		pnGame.add(lblDex);

		lblSpeed = new JLabel("Speed : " + user.getSpeed());
		lblSpeed.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSpeed.setForeground(Color.WHITE);
		lblSpeed.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		lblSpeed.setBounds(361, 174, 200, 40);
		pnGame.add(lblSpeed);

		lblPause = new JLabel(": Enter P");
		lblPause.setIcon(new ImageIcon(GameGUI.class.getResource("/ImageFile/Pause.png")));
		lblPause.setForeground(Color.WHITE);
		lblPause.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
		lblPause.setBounds(27, 23, 208, 43);
		pnGame.add(lblPause);

		this.addKeyListener(this); // To receive key input
	}

	/* Method executed when multithreading is in progress */
	@Override
	public void run() {
		try { /* Exception Handling */
			int starTimer = 0; // Counter variable that allows the star to fire at the right time
			int enemyTimer = 0; // Counter variable that allows enemies to spawn at the right time.
			double enemyType = 0; // Variable for enemy's type

			int timer = 20; 
			while (true) { // Repeat every 0.02 seconds. The interval was adjusted using the Thread.sleep method.
				Thread.sleep(timer);

				/* Case of the game needs to run */ 
				if (start) { 					
					/* Create star proportional to attack speed */
					if (starTimer >= 500 / user.getDex()) {
						fireStar(); // Method to create star 
						starTimer = 0; // Initialize the counter to 0.
					} // end if (starTimer)

					/* Create enemy proportional to user's level */
					if (enemyTimer >= 400 - 10 * user.getLevel()) {
						/* If the difficulty is easy, one enemy is created by type 1 or 2 */
						if (difficulty == MenuGUI.EASY) {
							enemyType = Math.random() * 2 + 1;
							enemyCreate((int) enemyType); // Method to create enemy 
						} 
						/* If the difficulty is hard, two enemys are created by type 3 or 4*/
						else if (difficulty == MenuGUI.HARD) {
							enemyType = Math.random() * 2 + 3;
							enemyCreate((int) enemyType); // Method to create enemy
							enemyType = Math.random() * 2 + 3;
							enemyCreate((int) enemyType); // Method to create enemy
						}
						enemyTimer = 0; // Initialize the counter to 0.
					} // end if (enemyTimer)

					/* increment counters. */
					starTimer += timer;
					enemyTimer += timer;

					this.keyControl(); // Method that moves the user based on left, right, up, and down
					// Redraws the JLabel based on what has been moved.
					lblUser.setBounds(user.getPositionX(), user.getPositionY(), user.getWidth(), user.getHeigth());

					this.moveStars(); // Method to move the stars
					this.moveEnemys(); // Method to move the enemys

					this.crashCheck(); // Method to check if star and enemy collided or if user and enemy collided
					this.checkScore(); /* Method to check whether the current score has exceeded 1000 points
					                    * or whether it is time to level up */

					this.playTime += (double) timer / 600; // Increase playTime.
					lblPlayTime.setText(String.format("Time %d:%02d", (int) this.playTime / 60, (int) this.playTime % 60));
					
					pnGame.repaint(); // Repaint pnGame. 
				} // end if (start)

				/* When user is game over */ 
				else if (gameOver) {
					GameOverDialog gameOverDialog = new GameOverDialog(
							this, (int) this.playTime, this.score, this.difficulty); // Opens the GameOverDialog window.
					// Receives the user's choice to play the game again or quit.
					int clickOption = gameOverDialog.showGameOverDialog();
					
					if (clickOption == GameOverDialog.YES) {
						/* When user wants to play the game again */
						dispose(); // Turn off the current screen
						
						// Run a new game using multithreading.
						Thread gameRestartThread = new Thread(new GameGUI(difficulty));
						gameRestartThread.start();
						break; // Terminate the current thread.
					}
					else if (clickOption == GameOverDialog.NO) {
						/* When user wants to quit the game, Turn off the current screen and open MenuGUI. */
						dispose();
						new MenuGUI();
						break; // Terminate the current thread.
					}
				} // end if (gameOver)

				/* When user is game clear */ 
				else if (clear) {
					ClearDialog clearDialog = new ClearDialog(
							this, (int) this.playTime, this.score, this.difficulty); // Opens the ClearDialog window.
					// Receives the user's choice to play the game again or quit.
					int clickOption = clearDialog.showClearDialog();
					
					if (clickOption == ClearDialog.YES) {
						/* When user wants to play the game again */
						dispose(); // Turn off the current screen
						
						// Run a new game using multithreading.
						Thread gameRestartThread = new Thread(new GameGUI(difficulty));
						gameRestartThread.start();
						break; // Terminate the current thread.
					} else if (clickOption == ClearDialog.NO) {
						/* When user wants to quit the game, Turn off the current screen and open MenuGUI. */
						dispose();
						new MenuGUI();
						break; // Terminate the current thread.
					}
				} // end if (clear)

				/* When user wants to go back to menu */
				else if (goMenu) {
					/* Turn off the current screen and open MenuGUI. */
					dispose();
					new MenuGUI();
					break;
				} // end if (goMenu)
				
			} // end while(true)
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	} // end method run

	/* Method to create star */
	public void fireStar() {
		// A star object is created based on the current user's location.
		Star star = new Star(user.getPositionX() + user.getWidth() / 2, user.getPositionY());
		starList.add(star);

		JLabel lblStar = new JLabel();
		lblStar.setBounds(star.getPositionX(), star.getPositionY(), star.getWidth(), star.getHeigth());
		lblStar.setIcon(star.getStarImage());
		pnGame.add(lblStar);
		lblStarList.add(lblStar);
	} // end method fireStar

	/* Method to create enemy */
	public void enemyCreate(int type) {
		// The x-coordinate is randomly generated.
		int positionX = (int) (Math.random() * (Main.SCREEN_WIDTH - 50));

		int hp = 0;
		// HP is different for each type of enemy and increases over time.
		switch (type) {
		case 1:
			hp = 3 + (int) this.playTime / 20;
			break;
		case 2:
			hp = 10 + (int) this.playTime / 10;
			break;
		case 3:
			hp = 5 + (int) this.playTime / 15;
			break;
		case 4:
			hp = 15 + (int) this.playTime / 7;
			break;
		}
		//  Speed is same for each type of enemy and increases over time.
		int speed = 5 + (int) this.playTime / 30;

		// Creates an enemy object with the values specified above.
		Enemy enemy = new Enemy(positionX, -50, hp, speed, type);
		enemyList.add(enemy);

		JLabel lblEnemy = new JLabel();
		lblEnemy.setBounds(enemy.getPositionX(), enemy.getPositionY(), enemy.getWidth(), enemy.getHeight());
		lblEnemy.setIcon(enemy.getEnemyImage());
		pnGame.add(lblEnemy);
		lblEnemyList.add(lblEnemy);
	} // end method enemyCreate

	/* Method that moves the user based on left, right, up, and down */
	public void keyControl() {
		/* Moves the user according to each boolean variable without leaving the screen. */
		if (0 < user.getPositionX() && left) {
			user.setPositionX(user.getPositionX() - user.getSpeed());
		}
		if (Main.SCREEN_WIDTH > user.getPositionX() + user.getWidth() + 15 && right) {
			user.setPositionX(user.getPositionX() + user.getSpeed());
		}
		if (0 < user.getPositionY() && up) {
			user.setPositionY(user.getPositionY() - user.getSpeed());
		}
		if (Main.SCREEN_HEIGHT > user.getPositionY() + user.getHeigth() + 40 && down) {
			user.setPositionY(user.getPositionY() + user.getSpeed());
		}
	} // end method keyControl

	/* Method to move the all stars in starList */
	public void moveStars() {
		for (int i = 0; i < starList.size(); i++) {
			Star star = starList.get(i);
			JLabel lblStar = lblStarList.get(i);

			if (star.getPositionY() < 0) {
				/* Remove the star if it goes off the screen. */
				starList.remove(i);
				lblStarList.remove(i);
				pnGame.remove(lblStar);
				i--;
			} else {
				star.moveStar(); // method to move each star object
				lblStar.setBounds(star.getPositionX(), star.getPositionY(), star.getWidth(), star.getHeigth());
			}
		} // end for
	} // end method moveStars

	/* Method to move the all enemys in enemyList */
	public void moveEnemys() {
		for (int i = 0; i < enemyList.size(); i++) {
			Enemy enemy = enemyList.get(i);
			JLabel lblEnemy = lblEnemyList.get(i);

			if (enemy.getPositionY() > Main.SCREEN_HEIGHT) {
				/* Remove the enemy if it goes off the screen. */
				enemyList.remove(i);
				lblEnemyList.remove(i);
				pnGame.remove(lblEnemy);
				i--;
			} else {
				enemy.moveEnemy(); // method to move each enemy object
				lblEnemy.setBounds(enemy.getPositionX(), enemy.getPositionY(), enemy.getWidth(), enemy.getHeight());
			}
		} // end for
	} // end method moveEnemys

	/* Method to check if star and enemy collided or if user and enemy collided */
	public void crashCheck() {
		Polygon p = null;

		/* Check if Enemys and Stars collide */
		for (int i = 0; i < starList.size(); i++) {
			Star star = starList.get(i);
			JLabel lblStar = lblStarList.get(i);

			for (int j = 0; j < enemyList.size(); j++) {
				Enemy enemy = enemyList.get(j);
				JLabel lblEnemy = lblEnemyList.get(j);

				// Create a star as a polygon and check for collision using the intersects method.
				int[] xPoints = { star.getPositionX(), star.getPositionX() + star.getWidth(),
						star.getPositionX() + star.getWidth(), star.getPositionX() };
				int[] yPoints = { star.getPositionY(), star.getPositionY(), star.getPositionY() + star.getHeigth(),
						star.getPositionY() + star.getHeigth() };
				p = new Polygon(xPoints, yPoints, 4);

				// Checks if any of the current polygons and enemy objects overlap.
				if (p.intersects((double) enemy.getPositionX(), (double) enemy.getPositionY(),
						(double) enemy.getWidth(), (double) enemy.getHeight())) {
					/* When star and enemy overlap, remove that star. */
					starList.remove(i);
					lblStarList.remove(i);
					pnGame.remove(lblStar);
					i--;

					enemy.setHp(enemy.getHp() - user.getAtk()); // Reduces the enemy's HP by the user's ATK.
					if (enemy.getHp() <= 0) {
						/* When the enemy is dead, increases score and removes the corresponding enemy. */
						score += enemy.getPoint();
						lblScore.setText("Score " + score);

						enemyList.remove(j);
						lblEnemyList.remove(j);
						pnGame.remove(lblEnemy);
						j--;
					} // end if (enemy.getHP() <= 0)
					break;
				} // end if (p.intersects)
			} // end for j (enemy)
		} // end for i (star)

		/* Check if Enemys and user collide */
		for (int i = 0; i < enemyList.size(); i++) {
			Enemy enemy = enemyList.get(i);
			JLabel lblEnemy = lblEnemyList.get(i);

			// Create a enemy as a polygon and check for collision using the intersects method.
			int[] xPoints = { user.getPositionX(), user.getPositionX() + user.getWidth(), user.getPositionX(),
					user.getPositionX() + user.getWidth() };
			int[] yPoints = { user.getPositionY(), user.getPositionY(), user.getPositionY() + user.getHeigth(),
					user.getPositionY() + user.getHeigth() };
			p = new Polygon(xPoints, yPoints, 4);

			// Checks if any of the current polygons and user overlap.
			if (p.intersects((double) enemy.getPositionX(), (double) enemy.getPositionY(), (double) enemy.getWidth(),
					(double) enemy.getHeight())) {
				/* When user and enemy overlap, Reduces the user's HP by Damage and updates the HP bar.*/
				user.setCurrentHP(user.getCurrentHP() - enemy.getDamage());
				progressBarHP.setValue(user.getCurrentHP());

				// Remove that enemy
				enemyList.remove(i);
				lblEnemyList.remove(i);
				pnGame.remove(lblEnemy);
				i--;

				// When the user's HP drops to 0, game over.
				if (user.getCurrentHP() <= 0) {
					user.setCurrentHP(0);
					start = false;
					gameOver = true;
				} // end if (user.getCurrentHP() <= 0)

			} // end if p.intersects
		} // end for i (enemy)
	} // end method crashCheck

	/* Method to score check */
	public void checkScore() {
		if (score >= 1000) {
			/* In case of Game Clear */
			score = 1000;
			lblScore.setText("Score " + score);
			start = false;
			clear = true;
		} // end if (score >= 1000)

		else if (score >= levelUpScore) {
			/* In case of level up */
			user.setLevel(user.getLevel() + 1);
			lblLevel.setText("Level : " + user.getLevel());

			// A statDialog displays to show the user the level-up information.
			StatDialog statDialog = new StatDialog(this); 
			int stat = statDialog.showStatDialog(); // Returned the user selected stat.

			/* Depending on the selected stat, the user's stat is raised. */
			if (stat == StatDialog.ATK) {
				user.setAtk(user.getAtk() + 1);
				lblAtk.setText("ATK : " + user.getAtk());
			} else if (stat == StatDialog.DEX) {
				user.setDex(user.getDex() + 1);
				lblDex.setText("DEX : " + user.getDex());
			} else if (stat == StatDialog.SPEED) {
				user.setSpeed(user.getSpeed() + 1);
				lblSpeed.setText("SPEED : " + user.getSpeed());
			}

			levelUpScore += 50; // Increases the number of points required to level up.

			// Reinitialize KeyPressed.
			left = false;
			right = false;
			up = false;
			down = false;
		} // end if (score >= levelUpScore)
	} // end method checkScore

	@Override
	public void keyTyped(KeyEvent ke) {

	} // end method keyTyped

	@Override
	public void keyPressed(KeyEvent ke) {
		switch (ke.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			left = true;
			break;
		case KeyEvent.VK_RIGHT:
			right = true;
			break;
		case KeyEvent.VK_UP:
			up = true;
			break;
		case KeyEvent.VK_DOWN:
			down = true;
			break;
		case KeyEvent.VK_P:
			/* Case of user wants to pause */
			start = false;

			// A pauseDialog is displayed and an action is selected.
			PauseDialog pauseDialog = new PauseDialog(this);
			int clickOption = pauseDialog.showPauseDialog();

			if (clickOption == PauseDialog.RESUME) {
				/* When the user chooses to continue playing, set start back to true. */
				start = true;
			} else if (clickOption == PauseDialog.MENU) {
				/* When the user chooses to go to the menu */
				goMenu = true;
			} else if (clickOption == PauseDialog.QUIT) {
				/* When the user wants to quit the game, exits the program. */
				System.exit(0);
			}

			// Reinitialize KeyPressed.
			left = false;
			right = false;
			up = false;
			down = false;
			break;
		} // end switch
	} // end method keyPressed

	@Override
	public void keyReleased(KeyEvent ke) {
		switch (ke.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			left = false;
			break;
		case KeyEvent.VK_RIGHT:
			right = false;
			break;
		case KeyEvent.VK_UP:
			up = false;
			break;
		case KeyEvent.VK_DOWN:
			down = false;
			break;
		}
	} // end method keyReleased
	
} // end class GameGUI
