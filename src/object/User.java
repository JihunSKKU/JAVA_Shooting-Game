package object;

import javax.swing.ImageIcon;

/* Class of user objects */
public class User {
	private int positionX;
	private int positionY;
	private int width;
	private int heigth;

	private int level; // It rises every 50 points.
	private int atk; // If user hits a star, it deals damage to the enemy equal to atk.
	private int dex; // In proportion to dex, the speed at which stars are fired increases.
	private int speed; // In proportion to the speed, the user's movement speed increases.

	private int currentHP; // Variable for user's currentHP

	private ImageIcon spaceshipImage;

	/* A constructor that takes no variables */
	public User() {
		// Initialize all variables
		setPositionX(275);
		setPositionY(600);
		setWidth(50);
		setHeigth(82);

		setLevel(1);
		setAtk(5);
		setDex(5);
		setSpeed(5);

		setCurrentHP(100);

		setSpaceshipImage(new ImageIcon(User.class.getResource("/ImageFile/Spaceship.png")));
	} // end constructor method
	
	/* Getter and setter */
	public int getPositionX() {
		return positionX;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeigth() {
		return heigth;
	}

	public void setHeigth(int heigth) {
		this.heigth = heigth;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getAtk() {
		return atk;
	}

	public void setAtk(int atk) {
		this.atk = atk;
	}

	public int getDex() {
		return dex;
	}

	public void setDex(int dex) {
		this.dex = dex;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getCurrentHP() {
		return currentHP;
	}

	public void setCurrentHP(int currentHP) {
		this.currentHP = currentHP;
	}

	public ImageIcon getSpaceshipImage() {
		return spaceshipImage;
	}

	public void setSpaceshipImage(ImageIcon spaceshipImage) {
		this.spaceshipImage = spaceshipImage;
	}
} // end class User
