package maria;

import java.awt.Image;


public class moveenemy implements Runnable {
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getStartx() {
		return startx;
	}

	public void setStartx(int startx) {
		this.startx = startx;
	}

	public int getStarty() {
		return starty;
	}

	public void setStarty(int starty) {
		this.starty = starty;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public boolean isIsleftorup() {
		return isleftorup;
	}

	public void setIsleftorup(boolean isleftorup) {
		this.isleftorup = isleftorup;
	}

	public int getUpmax() {
		return upmax;
	}

	public void setUpmax(int upmax) {
		this.upmax = upmax;
	}

	public int getDownmax() {
		return downmax;
	}

	public void setDownmax(int downmax) {
		this.downmax = downmax;
	}

	public Thread getThread() {
		return thread;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	}

	private int x = 0;
	private int y = 0;
	private int startx;
	private int starty;
	private int type;
	private Image image;
	private int imagetype = 0;
	private boolean isleftorup = true;
	private int upmax = 0;
	private int downmax = 0;
	private Thread thread;
	private backgrond backgrond;

	public backgrond getBackgrond() {
		return backgrond;
	}

	public void setBackgrond(backgrond backgrond) {
		this.backgrond = backgrond;
	}
	public void dead(){
		this.image=staticvalues.trangel.get(2);
		this.backgrond.enemy.remove(this);
		this.backgrond.remove.add(this);
	}
	@Override
	public void run() {
		while (true) {

			if (type == 1)
			{
				if (this.isleftorup) {
					this.x -= 3;
				} else {
					this.x += 3;
				}
				if (imagetype == 0) {
					imagetype = 1;
				} else {
					imagetype = 0;
				}
				this.image = staticvalues.trangel.get(imagetype);
				boolean canLeft = true;
				boolean canRight = true;
			
				boolean onLand = false;
				for (int i = 0; i < this.backgrond.obstraction.size(); i++) {
					enemy ob = backgrond.obstraction.get(i);
			
					if (ob.getX() == this.x + 60
							&& (ob.getY() + 50 > this.y && ob.getY() - 50 < this.y)) {
						isleftorup = true;
					}
					
					if (ob.getX() == this.x - 60
							&& (ob.getY() + 50 > this.y && ob.getY() - 50 < this.y)) {
						isleftorup = false;
					}

				}
				if(x < 0)
				{
					if(isleftorup == true)
					{
						isleftorup = false;
					}
					else {
						isleftorup = true;
					}
				}


			}
			if (type == 2) {
				if (this.isleftorup) {
					this.y -= 3;
				} else {
					this.y += 3;
				}
				if (imagetype == 0) {
					imagetype = 1;
				} else {
					imagetype = 0;
				}
				this.image = staticvalues.flower.get(imagetype);
				if (this.isleftorup && this.y <= this.upmax) {
					this.isleftorup = false;
				}
				if (!this.isleftorup && this.y >= this.downmax) {
					this.isleftorup = true;
				}
			}

			try {
				Thread.sleep(50);
			} catch (Exception e) {
				
			}
		}
	}

	public void reset() {

	}

	public moveenemy(int x, int y, boolean isleft, int type, backgrond backgrond) {
		this.x = x;
		this.y = y;
		this.isleftorup = isleft;
		this.type = type;
		this.backgrond = backgrond;
		if (type == 1) {
			this.image = staticvalues.trangel.get(0);
		}
		thread = new Thread(this);
		thread.start();
	}


	public moveenemy(int x, int y, boolean isup, int type, int upmax,
			int downmax, backgrond backgrond) {
		this.x = x;
		this.y = y;
		this.isleftorup = isup;
		this.type = type;
		this.upmax = upmax;
		this.downmax = downmax;
		this.backgrond = backgrond;
		if (type == 2) {
			this.image = staticvalues.flower.get(0);
		}
		thread = new Thread(this);
		thread.start();
	}
}
