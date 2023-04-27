package object;

import main.*;
import javax.swing.ImageIcon;

/* Class of enemy objects */
public class Enemy {
	private int positionX;
	private int positionY;
	private int width;
	private int height;

	private int hp; // HP is different for each type of enemy and increases over time.
	private int damage; // Damage is different for each type of enemy.
	private int point; // Point is different for each type of enemy.
	private int speed; // Speed is same for each type of enemy and increases over time.
	
	// Required variables to make enemies move left and right when move down (Counter)
	private int moveLeftRight;
	// ImageIcon to enter Enemy's label
	private ImageIcon enemyImage;

	/* A constructor that takes 5 variables */
	public Enemy(int positionX, int positionY, int hp, int speed, int type) {
		this.setPositionX(positionX);
		this.setPositionY(positionY);
		this.setWidth(50);
		this.setHeight(50);

		this.setHp(hp);
		this.setSpeed(speed);
		this.setMoveLeftRight(15);

		switch (type) { // Set damage, point, and imageIcon according to the type.
		case 1:
			this.setDamage(10);
			this.setPoint(2);
			this.setEnemyImage(new ImageIcon(Enemy.class.getResource("/ImageFile/Enemy/Enemy1.png")));
			break;
		case 2:
			this.setDamage(20);
			this.setPoint(5);
			this.setEnemyImage(new ImageIcon(Enemy.class.getResource("/ImageFile/Enemy/Enemy2.png")));
			break;
		case 3:
			this.setDamage(10);
			this.setPoint(2);
			this.setEnemyImage(new ImageIcon(Enemy.class.getResource("/ImageFile/Enemy/Enemy3.png")));
			break;
		case 4:
			this.setDamage(20);
			this.setPoint(4);
			this.setEnemyImage(new ImageIcon(Enemy.class.getResource("/ImageFile/Enemy/Enemy4.png")));
			break;
		} // end switch (type)
	} // end constructor method

	/* Method to move the enemy */
	public void moveEnemy() {
		this.positionY += speed; // Increase positionY by speed.
		
		if (this.moveLeftRight < 30) {
			/* Case to move right */
			if (this.positionX + this.width >= Main.SCREEN_WIDTH) {
				/* Case to go out of screen */
				this.moveLeftRight = 30;
				this.positionX -= 5;
			} else {
				this.positionX += 5;
			}
		}
		else {
			/* Case to move left */
			if(this.positionX <= 0) {
				/* Case to go out of screen */
				this.moveLeftRight = 0;
				this.positionX += 5;
			} else {
				this.positionX -= 5;
			}
		}
		
		this.moveLeftRight++; // Counter of move left or right
		if(this.moveLeftRight >= 60)
			this.moveLeftRight = 0; // The counter keeps incrementing and then goes back to 0.
	} // end method moveEnemy

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

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public ImageIcon getEnemyImage() {
		return enemyImage;
	}

	public void setEnemyImage(ImageIcon enemyImage) {
		this.enemyImage = enemyImage;
	}

	public int getMoveLeftRight() {
		return moveLeftRight;
	}

	public void setMoveLeftRight(int moveLeftRight) {
		this.moveLeftRight = moveLeftRight;
	}
} // end class Enemy
