package Main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class BadguyBoss2 extends GameObject{
	
	private Handler handler;
	Random r = new Random();
	
	private int timer = 80;
	private int timer2 = 50;
	private BufferedImage image5;
	
	public BadguyBoss2(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;

		velX = 0;
		velY = 2;
		
try {
			
			image5 = ImageIO.read(getClass().getResourceAsStream("/bossphoto.png"));
		} catch(IOException e) {
			e.printStackTrace();
		}

	}
	

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 96, 96);
	}

	public void tick() {
		x += velX;
		y += velY;
		
		if(timer <= 2) velY = 0;
		else timer--;
		
		if(timer <= 0) timer2--;
		if(timer2 <= 0)
		{
			if(velX == 0) velX = 0;
			if(velX > 0)
			velX += 0.005f;
			else if(velX < 0)
			velX -= 0.005f;
			
			velX = MyCanvas.clamp(velX, -10, 10);
			
			int spawn = r.nextInt(10);
			if(spawn == 0) handler.addObject(new BossProjectile((int)x+48, (int)y+48, ID.BasicEnemy, handler));
		}

	//	if(y <= 0 || y >= Game.HEIGHT - 32) velY *= -1;
		if(x <= 0 || x >= MyCanvas.WIDTH - 48) velX *= -1;
		
		//handler.addObject(new Trail(x, y, ID.Trail, Color.red, 96, 96, 0.008f, handler));
		
	}


	public void render(Graphics g) {
		//g.setColor(Color.red);
		g.fillRect((int)x, (int)y, 96, 96);
		g.drawImage(image5, (int)x, (int)y, 96, 96, null);
		
	}

}
