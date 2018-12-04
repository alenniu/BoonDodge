package Main;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public abstract class GameObject {//where all the objects are placed( GOOD GUY,ENEMY, COINS)
//protected means like public but only works on whoever inherits the GameObject
	
	protected float x, y;
	protected ID id;
	protected float velX, velY;//this is the speed
	private Image img;
	//this is the x y and id 
	public GameObject(float x, float y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;

	}
	//this loads in the abstracts for the players
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
//all these are getters and setter. they work whenever I use setX() whatever number in those brackets is int x
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public void setId(ID id) {
		this.id = id;
	}
	public ID getId() {
		return id;
	}
	public void setVelX(int velX) {
		this.velX = velX;
	}
	public void setVelY(int velY) {
		this.velY = velY;
	}
	
	public float getVelX() {
		return velX;
	}
	public float getVelY() {
		return velY;
	}
	public void setImg(String imgpath) {
		this.img = Toolkit.getDefaultToolkit().getImage(imgpath);
	}
	
	public Image getImg() {
		return img;
	}
	
	
}


