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

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class Player3 extends GameObject {

	Random r = new Random();
	Handler handler;
	private Image img;
	private BufferedImage image4;
	

	public Player3(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		try {
			
			image4 = ImageIO.read(getClass().getResourceAsStream("/kBjvAi.jpg"));
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
		for(int i = 0; i < handler.object.size(); i++) {
			
			
			GameObject tempObject = handler.object.get(i);
			
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
		if(id == ID.Player2) g.setColor(Color.blue); 
	//	else if(id == ID.Player2) g.setColor(Color.blue); 
		g.fillRect((int)x, (int)y, 32, 32);
		g.drawImage(image4, 0, 0, 640, 480, null);
		
	}
	public void setImg(String imgpath) {
		this.img = Toolkit.getDefaultToolkit().getImage(imgpath);
	}
	public Image getImg() {
		return img;
	}
	
	
	

}
