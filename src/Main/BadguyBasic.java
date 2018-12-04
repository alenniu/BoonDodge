package Main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BadguyBasic extends GameObject{
	
	private Handler handler;

	public BadguyBasic(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;

		velX = 5;
		velY = 5;
		
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 16, 16);
	}

	public void tick() {
		x += velX;
		y += velY;

		if(y <= 0 || y >= MyCanvas.HEIGHT - 32) velY *= -1;
		if(x <= 0 || x >= MyCanvas.WIDTH - 16 ) velX *= -1;
		
		//handler.addObject(new Trail(x, y, ID.Trail, Color.red, 16, 16, 0.02f, handler));
		
	}


	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillRect((int)x, (int)y, 16, 16);
		
	}

}
