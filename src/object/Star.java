package object;
import javax.swing.ImageIcon;

/* Class of star objects */
public class Star {
	private int positionX;
	private int positionY;
	private int width;
	private int heigth;
	private int speed;
	private ImageIcon starImage;

	/* A constructor that takes 2 variables */
	public Star(int positionX, int positionY) {
		setPositionX(positionX-7); // Make it fire from the middle of the user.
		setPositionY(positionY);
		setWidth(15);
		setHeigth(15);
		setSpeed(30);
		setStarImage(new ImageIcon(Star.class.getResource("/ImageFile/Star.png")));
	} // end constructor method

	/* Method to move star */
	public void moveStar() {
		this.positionY -= this.speed;
	} // end method moveStar
	
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

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public ImageIcon getStarImage() {
		return starImage;
	}

	public void setStarImage(ImageIcon starImage) {
		this.starImage = starImage;
	}
} // end class Star
