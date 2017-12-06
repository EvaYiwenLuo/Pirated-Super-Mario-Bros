package maria;

import java.awt.Image;

public class mario implements Runnable {
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	private int x; 
	private int y;
	private String status; 
	private Image image; 
	public int xmove = 0;
	public int ymove = 0;
	private int p = 0;
	private int a = 1;
	public int time = 0; 
	public Thread thread = null;
	private backgrond backgrond = null; 

	public backgrond getBackgrond() {
		return backgrond;
	}

	public void setBackgrond(backgrond backgrond) {
		this.backgrond = backgrond;
	}

	public int getSocre() {
		return socre;
	}

	public void setSocre(int socre) {
		this.socre = socre;
	}

	public int getLift() {
		return lift;
	}

	public void setLift(int lift) {
		this.lift = lift;
	}

	private int socre;
	private int lift;

	public mario(int x, int y) {
		this.x = x;
		this.y = y;
 		image = staticvalues.mariao.get(0);
		this.socre = 0;
		this.lift = 3;
		this.status = "right-standing";
		thread = new Thread(this);
		thread.start();
	}

	public void leftMove() {
		xmove = -5;
		this.status = "left-moving";
	}

	public void rightMove() {
		xmove = 5;
		this.status = "right-moving";
	}

	public void leftstop() {
		xmove = 0;
		this.status = "left-standing";
	}

	public void rightstop() {
		xmove = 0;
		this.status = "right-standing";
	}

	public void jump() {
		if (this.status.indexOf("jumping") == -1) {
			if (this.status.indexOf("left") != -1) {
				this.status = "left-jumping";
			} else {
				this.status = "right-jumping";
			}
			ymove = -10;
			time = 18;
		}
	}

	public void down() {
		if (this.status.indexOf("left") != -1) {
			this.status = "left-jumping";
		} else {
			this.status = "right-jumping";
		}
		ymove = 10;
	}

	public boolean die = true;

	public void dead() {
		this.image = staticvalues.die;
	}

	private boolean isClear = false;

	@Override
	public void run() {
		while (true) {
			System.out.println(die);
			if (die == false) {
              
			} else
 			{
				boolean canleft = true;
				boolean canright = true;
				boolean jumb = false;
				if (backgrond.trutel != null
						&& backgrond.trutel.y == this.y + 60
						&& (backgrond.trutel.x + 60 > this.x && backgrond.trutel.x - 60 < this.x)) {
					backgrond.trutel.type = 3;
					this.time = 10;
					this.ymove = -5;
				}
				if (backgrond.trutel != null
						&& backgrond.trutel.x + 50 > this.x
						&& backgrond.trutel.x - 50 < this.x
						&& backgrond.trutel.y + 50 > this.y
						&& backgrond.trutel.y - 50 < this.y) {
					this.dead();
					System.out.println("ÒÑËÀ");
					die = false;
				}
				for (int i = 0; i < backgrond.obstraction.size(); i++) {
					enemy ob = backgrond.obstraction.get(i);
 					if ((ob.getX() == this.x + 50)
							&& (ob.getY() + 50 > this.y && ob.getY() - 50 < this.y)) {
						canright = false;
					}
 					if ((ob.getX() == this.x - 50)
							&& (ob.getY() + 50 > this.y && ob.getY() - 50 < this.y)) {
						canleft = false;
					}
 					if (ob.getY() == this.y + 50 && ob.getX() + 50 > this.x
							&& ob.getX() - 50 < this.x) {
						jumb = true;
					}
 					if (ob.getY() == this.y - 60
							&& (ob.getX() + 50 > this.x && ob.getX() - 50 < this.x)) {
 						if (ob.getType() == 0) {
							this.backgrond.obstraction.remove(ob);
							this.backgrond.removedenemy.add(ob);
						}

 						if (ob.getType() == 4 || ob.getType() == 3 && time > 0) {
							ob.setType(2);
							ob.setImage();
						}

						time = 0;
					}

				}
				if (jumb && time == 0) {
					if (this.status.indexOf("left") != -1) {
						if (xmove != 0) {
							status = "left-moving";
						} else {
							status = "left-standing";
						}
					} else {
						if (xmove != 0) {
							status = "right-moving";
						} else {
							status = "right-standing";
						}
					}
				} else {
 					if (time != 0) {
						time -= 1;
					} else {
						this.down();
					}
					y += ymove;
				}

				if (canleft && xmove < 0 || canright && xmove > 0) {
					if (x < 0) {
						x = 0;
					} else {
						x += xmove;
					}
				}
				int temp = 0;
 				if (this.status.indexOf("left") != -1) {
					temp += 5;
				}
 				if (this.status.indexOf("moving") != -1) {
					temp += p;
					if (a % 4 == 1) {
						p++;
						if (p == 4) {
							p = 0;
						}
					}
				}
				if (this.status.indexOf("jumping") != -1) {
					temp += 4;
				}
				this.image = staticvalues.mariao.get(temp);
				a++;
 				for (int i = 0; i < this.backgrond.enemy.size(); i++) {
					moveenemy e = (moveenemy) this.backgrond.enemy.get(i);
					if (e.getX() + 50 > this.x && e.getX() - 50 < this.x
							&& e.getY() + 50 > this.y && e.getY() - 50 < this.y) {
						this.dead();
						die = false;
					}
					if (e.getY() == this.y + 60
							&& (e.getX() + 60 > this.x && e.getX() - 60 < this.x)) {
						if (e.getType() == 1) {
							e.dead();
							this.time = 10;
							this.ymove = -5;
						} else if (e.getType() == 2) {
							this.dead();
							die = false;

						}
					}
				}
				try {
					Thread.sleep(50);

					if (a == 100) {
						a = 0;
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
