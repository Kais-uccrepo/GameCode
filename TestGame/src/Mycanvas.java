import java.awt.*;
import java.awt.event.*;
import sun.audio.*;
import java.io.InputStream;
import java.util.Random;
import java.io.FileInputStream;
import java.io.IOException;

public class Mycanvas extends Canvas implements KeyListener,MouseListener {

	// MHOEL - create image - default looking right
	String imgname = "testassets-master/right.png";
	
	Image poke = Toolkit.getDefaultToolkit().getImage(imgname); 
	int x = 10;
	int y = 10;
	//Rectangle rect = new Rectangle(200,300,100,100);
	
	// Make an empty array for badguys
	Badguy badguys[] = new Badguy[5];

	public Mycanvas() {
		this.setSize(600,400);
		
		// MHOEL - set background to be white
		this.setBackground(Color.white);
		
		this.addKeyListener(this);
		this.addMouseListener(this);
		this.setFocusable(true);
		
		// MHOEL - fill an array of stationary bad guys
		Random rand = new Random();
		for (int i = 0; i<5; i++) {
			badguys[i] = new Badguy(rand.nextInt(600), rand.nextInt(400), 50, 50);
		}
		
		playIt("testassets-master/storm.wav");
	}

	public static void playIt(String filename) {
		try {
			InputStream in = new FileInputStream(filename);
			AudioStream as = new AudioStream(in);
			AudioPlayer.player.start(as);
		} catch (IOException e) {
			System.out.println("can not find file");
		}
	}

	@Override
	public void keyPressed(KeyEvent pressEvent) {
		if (x > this.getWidth()) {
			x = x - 25;
		}
		if (y > this.getHeight()) {
			y = y - 25;
		}
		
		
		if (pressEvent.getKeyCode() == 39) {
			// MHOEL - if image is right, change to runright and vice versa
			if (imgname == "testassets-master/right.png") {
				imgname = "testassets-master/runright.png";
			} else {
				imgname = "testassets-master/right.png";
			}
			poke = Toolkit.getDefaultToolkit().getImage(imgname);
			x = x + 5;
		}
		this.repaint();
		
		if (pressEvent.getKeyCode() == 37) {
			x = x - 5;
			if (imgname == "testassets-master/left.png") {
				imgname = "testassets-master/runleft.png";
			} else {
				imgname = "testassets-master/left.png";
			}
			poke = Toolkit.getDefaultToolkit().getImage(imgname);
			
		}
		this.repaint();
		
		if (pressEvent.getKeyCode() == 38) {
			y = y - 5;
			if (imgname == "testassets-master/up.png") {
				imgname = "testassets-master/uprun.png";
			} else {
				imgname = "testassets-master/up.png";
			}
			poke = Toolkit.getDefaultToolkit().getImage(imgname);
			
		}
		this.repaint();
		
		if (pressEvent.getKeyCode() == 40) {
			y = y + 5;
			if (imgname == "testassets-master/down.png") {
				imgname = "testassets-master/downrun.png";
			} else {
				imgname = "testassets-master/down.png";
			}
			poke = Toolkit.getDefaultToolkit().getImage(imgname);
			
		}
		
		if (pressEvent.getKeyCode() == 32) {
			imgname = "testassets-master/swordraise.png";
			poke = Toolkit.getDefaultToolkit().getImage(imgname);
		}
		this.repaint();
	}
	
	public boolean getContains(){
		boolean containsHero = false;
		Rectangle bgRect = new Rectangle();
		for (Badguy bg: badguys) {
			bgRect.x = bg.getXcoord();
			bgRect.y = bg.getYcoord();
			bgRect.width = bg.getWidth();
			bgRect.height = bg.getHeight();
			if (bgRect.contains(x,y)) {
				containsHero = true;
			}
		}
		return containsHero;
	}

	public void getHero() {
		boolean herocontains = false;
		Rectangle hRect = new Rectangle();
		hRect.x = x;
		hRect.y = y;
		hRect.width = 55;
		hRect.height = 55;
			for(Badguy bg:badguys) {
				/*
				if(hRect.contains(bg.getXcoord(), bg.getYcoord())) {
					herocontains = true;
					break;
				}
				*/
					if(hRect.contains(bg.getXcoord(), bg.getYcoord()) && imgname == "testassets-master/swordraise.png") {
						bg.setXcoord(100000);
						bg.setYcoord(100000);
						this.repaint();
						break;
					}
			}

		
	}

	public void paint(Graphics g) {
		
		// MHOEL - draw stationary badguys
		for (Badguy bg: badguys) {
			g.fillRect(bg.getXcoord(), bg.getYcoord(), bg.getWidth(), bg.getHeight());
		}	
		//g.fillRect(x,y,95,99);
		g.drawImage(poke,x,y,95,99,this);
	}

	public void mouseClicked(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		//playIt("testassets-master/phaser2.wav");
		imgname = "testassets-master/right.png";
		poke = Toolkit.getDefaultToolkit().getImage(imgname);
		this.repaint();
	}
	public void mouseEntered(MouseEvent e) {
		System.out.print("Hey, get back in here!");
	}
	public void mouseExited(MouseEvent e) {
		System.out.print(e);
	}
	public void mousePressed(MouseEvent e) {
		System.out.print(e);
	}
	public void mouseReleased(MouseEvent e) {
		System.out.print(e);
	}
   @Override
	public void keyTyped(KeyEvent e) {

	}
	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == 32) {
			imgname = "testassets-master/swoosh.png";
			poke = Toolkit.getDefaultToolkit().getImage(imgname);
			this.repaint();
			playIt("testassets-master/swordsound.wav");
			
		}
	}

}
