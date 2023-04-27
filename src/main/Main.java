// 2019313611 Jihun Kim Final Project
package main;

import gui.*;

import java.awt.EventQueue;

/* Main class of my project */
public class Main {
	/* Game GUI size, global variable */
	public static final int SCREEN_WIDTH = 600;
	public static final int SCREEN_HEIGHT = 800;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new StartGUI();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
