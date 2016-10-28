package videoGame;

import javax.swing.JFrame;

/**
 * <p>Here is my game</p>
 * <p><b>Run code from here, not Mycanvas</b></p>
 * @author  kais.jessa
 * @since October 25 2016
 * @version 2.0
 *
 */

public class Myscreen extends JFrame {

	public Myscreen() {
		this.setSize(1200,800);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setTitle("ShrimSim 2017");
		this.setFocusable(true);
	}

	public static void main(String[] args) {
		Myscreen screen = new Myscreen();
		Mycanvas canvas = new Mycanvas();
		screen.getContentPane().add(canvas);
	}

}