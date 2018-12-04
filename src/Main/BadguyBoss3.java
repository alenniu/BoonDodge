package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class BadguyBoss3 extends GameObject{
	
	private Handler handler;
	Random r = new Random();
	
	private int timer = 80;
	private int timer2 = 50;
	private BufferedImage image5;
	
	public BadguyBoss3(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;

		

	}
	

	public Rectangle getBounds() {
		return new Rectangle(480, 430, 96, 96);
	}

	public void tick() {
		
	//	if(y <= 0 || y >= Game.HEIGHT - 32) velY *= -1;
		if(x <= 0 || x >= MyCanvas.WIDTH - 48) velX *= -1;
		
		//handler.addObject(new Trail(x, y, ID.Trail, Color.red, 96, 96, 0.008f, handler));
		
	}


	public void render(Graphics g) {
		Font fnt2 = new Font("arial", 1, 30);
		g.setFont(fnt2);
		g.setColor(Color.red);
	
		
		g.drawString("HAHAHA I am the ULTIMATE BOSS", MyCanvas.WIDTH / 2 -250, 115);
		g.drawString("Prepare TO FACE MY WRATH", MyCanvas.WIDTH / 2 -220, 150);
		
		
	}

}
