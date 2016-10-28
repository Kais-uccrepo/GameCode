package videoGame;

import java.awt.*;
import java.awt.event.*;
import sun.audio.*;
import java.io.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


/*
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener; */

public class Mycanvas extends Canvas implements KeyListener, MouseListener {
	Image img = Toolkit.getDefaultToolkit().getImage("Files/Shrimad.jpeg");
	int shrimx = 10;
	int shrimy = 10;
	int speed = 10;
	boolean soundeffect = true;
	Rectangle rect = new Rectangle(200,300,100,100);

	public Mycanvas() {
		this.setSize(1200,800);
		this.addKeyListener(this);
		this.addMouseListener(this);
		//this.setFocusable(true);
		playIt("Files/backroundmudic.wav");
	}

	public void paint(Graphics g) {
		//g.setColor(Color.RED);
		//g.fillOval(99,100,399,99);
	
		g.drawImage(img,shrimx,shrimy,200,150,this);
		g.fillRect((int)rect.getX(),(int)rect.getY(),(int)rect.getWidth(),(int)rect.getHeight());

	}
	
	public void mouseClicked(MouseEvent e) {
		//System.out.println("Click");
	}
	public void mouseEntered(MouseEvent e) {
		//System.out.println(e);
	}
	public void mouseExited(MouseEvent e) {
		
	}
	public void mousePressed(MouseEvent e) {

		if (shrimx > this.getWidth() - 202) {
			shrimx = shrimx - speed;
		}

		if (shrimy > this.getHeight() - 161) {
			shrimy = shrimy - speed;
		}

		if (shrimx < 0) {
			shrimx = shrimx + speed;
		}

		if (shrimy < 0) {
			shrimy = shrimy + speed;
		}

		if (rect.contains(shrimx,shrimy)) {
			shrimx = 10;
			shrimy = 10;
		} else {
			shrimx = e.getX();
			shrimy = e.getY();
		}
		this.repaint();
	}
	public void mouseReleased(MouseEvent e) {
		//System.out.print(e);
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == 39) {
			shrimx = shrimx + speed;
			//playIt("verynice.wav");
			this.repaint();
		} 
		if (e.getKeyCode() == 37) {
			shrimx = shrimx - speed;
			this.repaint();
			//playIt("verynice.wav");
		}
		if (e.getKeyCode() == 38) {
			shrimy = shrimy - speed;
			this.repaint();
			//playIt("verynice.wav");
		}
		if (e.getKeyCode() == 40) {
			shrimy = shrimy + speed;
			this.repaint();
			//playIt("verynice.wav");
		}
		if (e.getKeyCode() == 32) {
			img = Toolkit.getDefaultToolkit().getImage("Files/Shrimdab.jpeg");
			this.repaint();
			if(soundeffect == true) {
				playIt("Files/sound2.wav");
				soundeffect = false;
			}
		}
		if (e.getKeyCode() == 27) {
			System.exit(0);
		}
		if (shrimx > this.getWidth() - 202) {
			shrimx = shrimx - speed;
		}
		if (shrimy > this.getHeight() - 161) {
			shrimy = shrimy - speed;
		}
		if (shrimx < 0) {
			shrimx = shrimx + speed;
		}
		if (shrimy < 0) {
			shrimy = shrimy + speed;
		}
		if (rect.contains(shrimx,shrimy)) {
			shrimx = 10;
			shrimy = 10;
		}
		this.repaint();

		}
	@Override
	public void keyReleased(KeyEvent e) {
		//System.out.println(e);
		if (e.getKeyCode() == 32) {
			img = Toolkit.getDefaultToolkit().getImage("Files/Shrimad.jpeg");
			this.repaint();
			soundeffect = true;
			
		}
	}
	@Override
	public void keyTyped(KeyEvent e) {
		//System.out.println(e);
	}
	public static void playIt(String MySong) {
		try {
			InputStream in = new FileInputStream(MySong);
			AudioStream as = new AudioStream(in);
			AudioPlayer.player.start(as);
			
		} catch (IOException e) {
			System.out.println("y u do dis");
		}
		
	}
}
