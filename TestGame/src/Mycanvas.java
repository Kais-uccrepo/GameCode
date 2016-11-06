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
	String vilimg = "testassets-master/vil1.png";
	int timeticker = 25;
	int timer = 0;
	//int linkspeedx = 0;
	//int linkspeedy = 0;
	
	Image poke = Toolkit.getDefaultToolkit().getImage(imgname); 
	Image vil = Toolkit.getDefaultToolkit().getImage(vilimg);
	int x = 10;
	int y = 10;
	//Rectangle rect = new Rectangle(200,300,100,100);
	
	// Make an empty array for badguys
	Badguy badguys[] = new Badguy[5];

	public Mycanvas() {
		this.setSize(1000,1000);
		
		// MHOEL - set background to be white
		this.setBackground(Color.white);
		
		this.addKeyListener(this);
		this.addMouseListener(this);
		this.setFocusable(true);
		
		// MHOEL - fill an array of stationary bad guys
		Random rand = new Random();
		for (int i = 0; i<5; i++) {
			badguys[i] = new Badguy(rand.nextInt(this.getWidth()-55), rand.nextInt(this.getHeight()-55), 45, 51);
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
			x = -55;
		}
		if (y > this.getHeight()) {
			y = -55;
		}
		if (x < -55) {
			x = this.getWidth();
		}
		if (y < -55) {
			y = this.getHeight();
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
			//linkspeedx = 5;
		}
		this.repaint();
		
		if (pressEvent.getKeyCode() == 37) {
			x = x - 5;
			//linkspeedx = -5;
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
			//linkspeedy = -5;
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
			//linkspeedy = 5;
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
		//linkrun();
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
					if(hRect.contains(bg.getXcoord(), bg.getYcoord()) && imgname == "testassets-master/swoosh.png") {
						bg.setXcoord(-100);
						bg.setYcoord(-100);
						this.repaint();
						break;
					}
			}

		
	}
/*	
	public void linkrun() {
		x = x + //linkspeedx;
		y = y + //linkspeedy;
		this.repaint();
	}
*/
	public void paint(Graphics g) {
		
		// MHOEL - draw stationary badguys
		getHero();
		
		if (timer == timeticker) {
			if(vilimg == "testassets-master/vil1.png") {
				vilimg = "testassets-master/vil2.png";
			}else if(vilimg == "testassets-master/vil2.png") {
				vilimg = "testassets-master/vil1.png";
			}
			timer = 0;
		}else{
			timer+=1;
		}
		Image vil = Toolkit.getDefaultToolkit().getImage(vilimg);
		this.repaint();
		for (Badguy bg: badguys) {
			//g.fillRect(bg.getXcoord(), bg.getYcoord(), bg.getWidth(), bg.getHeight());
			g.drawImage(vil, bg.getXcoord(), bg.getYcoord(), bg.getWidth(), bg.getHeight(),this);

			
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
		/*
		if (e.getKeyCode() == 37 || e.getKeyCode() == 39) {
			//linkspeedx = 0;
			
		}
		
		if (e.getKeyCode() == 38 || e.getKeyCode() == 40) {
			//linkspeedy = 0;
		}
		*/
	}
	

}

