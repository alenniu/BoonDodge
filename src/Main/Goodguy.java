package Main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import javax.imageio.ImageIO;

import Main.MyCanvas.STATE;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class Goodguy extends GameObject {

	Random r = new Random();
	Handler handler;
	private Image img;
	private BufferedImage image3;
	private BufferedImage image6;
	public enum STATE {
		Menu,
		Help,
		Game,
		Skin1,
		End
	};
	public static STATE gameState = STATE.Menu;

	public Goodguy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		try {
			
			image3 = ImageIO.read(getClass().getResourceAsStream("/square1.png"));
			image6 = ImageIO.read(getClass().getResourceAsStream("/skin11.jpg"));
		} catch(IOException e) {
			e.printStackTrace();
		}

	}
	
	public void playIt(String filename) {
		
		try {
			InputStream in = new FileInputStream(filename);
			AudioStream as = new AudioStream(in);
			AudioPlayer.player.start(as);
		} catch (IOException e) {
			System.out.println(e);
		}

		}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}
	public void tick() {
		x += velX;
		y += velY;
		
		x = MyCanvas.clamp(x, 0, MyCanvas.WIDTH - 37);
		y = MyCanvas.clamp(y, 0, MyCanvas.HEIGHT - 60);
		
		//handler.addObject(new Trail(x, y, ID.Trail, Color.white, 32, 32, 0.05f, handler));
		
		collision();
		
		
	}
	
	private void collision() {
		for(int i = 0; i < handler.object.size(); i++) { //loop to repeat collision.
			
			
			GameObject tempObject = handler.object.get(i); // setting a temporary object 
			if(tempObject.getId() == ID.Hp){//tempObject is now hp pack because passed this code.
				if(getBounds().intersects(tempObject.getBounds())) {  //if good guy intersects hp pack
					//collision code
					HUD.HEALTH +=25;// add 25 health
					handler.removeObject(tempObject); // remove object
					playIt("files/hpget.wav"); // plays the gain hp sound
			
		}
		
			}
			if(tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy || tempObject.getId() == ID.SmartEnemy){//tempObject is now BasicEnemy because passed this code.
				if(getBounds().intersects(tempObject.getBounds())) {
					//collision code
					HUD.HEALTH -=2;
					playIt("files/velcro-strap-1.wav");
					
					
					
				}
		}
		}
	}
//this is a render
	public void render(Graphics g) {
		
		if(id == ID.Player) g.setColor(Color.white); 
		
	//	else if(id == ID.Player2) g.setColor(Color.blue); 
		g.fillRect((int)x, (int)y, 32, 32);
		
		g.drawImage(image3, (int)x, (int)y, 32, 32, null);
		if(id == ID.Skin1) g.drawImage(image6, (int)x, (int)y, 32, 32, null);
		
		
	}
	public void setImg(String imgpath) {
		this.img = Toolkit.getDefaultToolkit().getImage(imgpath);
	}
	public Image getImg() {
		return img;
	}
	
	
	

}
