package maria;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.text.html.HTMLDocument.Iterator;

public class myframe extends JFrame implements Runnable {

	private ArrayList<backgrond> backgronds = new ArrayList<>();
	private backgrond backgrondnow = null;
	private mario mario;

	public myframe() {
		// TODO Auto-generated constructor stub
		this.setTitle("É½Õ¯ÂíÀï°Â");
		this.setSize(900, 600);
		this.setLocationRelativeTo(null);
		staticvalues.init();
		backgrondnow = new backgrond(0, false);
		for (int i = 1; i <= 3; i++) {
			this.backgronds.add(new backgrond(i, i == 3 ? true : false));
		}
		this.backgrondnow = backgronds.get(0);
		mario = new mario(0, 480);
		mario.setBackgrond(backgrondnow);
		this.repaint();
		this.addKeyListener(new key());
		Thread thread = new Thread(this);
		thread.start();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);

	}

	@Override
	public void paint(Graphics graphics) {
		BufferedImage bufferedImage = new BufferedImage(900, 600,
				BufferedImage.TYPE_3BYTE_BGR);
		Graphics graphics2 = bufferedImage.getGraphics();
		graphics2.drawImage(this.backgrondnow.getBackgroundImage(), 0, 0, this);
		
		java.util.Iterator<moveenemy> iterator2 = this.backgrondnow.enemy
				.iterator();
		while(iterator2.hasNext())
		{
			moveenemy moveenemy = iterator2.next();
			graphics2.drawImage(moveenemy.getImage(), moveenemy.getX(), moveenemy.getY(), this);
		}
	
		java.util.Iterator<enemy> iterator = this.backgrondnow.obstraction
				.iterator();
		while (iterator.hasNext())
		{
			enemy obstraction = iterator.next();
			graphics2.drawImage(obstraction.getImage(), obstraction.getX(),
					obstraction.getY(), this);
		}
       if(backgrondnow.trutel != null)
       {
    	   graphics2.drawImage(backgrondnow.trutel.image,backgrondnow.trutel.x,backgrondnow.trutel.y,this);
       }
		graphics2.drawImage(mario.getImage(), mario.getX(), mario.getY(), this);
		graphics.drawImage(bufferedImage, 0, 0, this);
	}

	class key implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {

		}

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			{   
				myframe.this.mario.rightMove();
			}
			if (e.getKeyCode() == 37)
			{
				myframe.this.mario.leftMove();
			}
			if(e.getKeyCode() == 32)
			{
				myframe.this.mario.jump();
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == 39)
			{
				myframe.this.mario.rightstop();
			}
			if (e.getKeyCode() == 37)
			{
				myframe.this.mario.leftstop();
			}

		}

	}

	public static void main(String[] args) {
		myframe myframe = new myframe();
	}

	@Override
	public void run() {
		while (true) {
			this.repaint();
			try {
				Thread.sleep(50);
				if(mario.getX() >= 840)
				{
					
					this.backgrondnow = backgronds.get(this.backgrondnow.getSort());
					mario.setBackgrond(backgrondnow);
					mario.setX(0);
					mario.setY(480);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
