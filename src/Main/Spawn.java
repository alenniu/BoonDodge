package Main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.util.Timer;
import java.util.concurrent.TimeUnit.*;

public class Spawn {
	
	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	

	
	private float scoreKeep = 0;
	
	public Spawn(Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
	}
	
	public void tick() {
	
		
		scoreKeep++;
		
		if(scoreKeep >= 250) {
			scoreKeep = 0;
			hud.setLevel(hud.getLevel() + 1);
			
			if(hud.getLevel() == 2) {
				
			handler.addObject(new BadguyBasic(r.nextInt(MyCanvas.WIDTH - 50), r.nextInt(MyCanvas.HEIGHT - 50), ID.BasicEnemy, handler));
			}else if(hud.getLevel() == 3) {
				handler.addObject(new BadguyBasic(r.nextInt(MyCanvas.WIDTH - 50), r.nextInt(MyCanvas.HEIGHT - 50), ID.BasicEnemy, handler));
			}else if(hud.getLevel() == 4) {
				handler.addObject(new BadguyFast(r.nextInt(MyCanvas.WIDTH - 50), r.nextInt(MyCanvas.HEIGHT - 50), ID.FastEnemy, handler));
				handler.addObject(new Hp(r.nextInt(MyCanvas.WIDTH - 50), r.nextInt(MyCanvas.HEIGHT - 50), ID.Hp, handler));
			}else if(hud.getLevel() == 5) {
			//	handler.addObject(new Player3(MyCanvas.WIDTH/2-32, MyCanvas.HEIGHT/2-32, ID.Player3, handler));
				handler.addObject(new SmartEnemy(r.nextInt(MyCanvas.WIDTH - 50), r.nextInt(MyCanvas.HEIGHT - 50), ID.SmartEnemy, handler));
			}else if(hud.getLevel() == 6) {
				handler.addObject(new BadguyFast(r.nextInt(MyCanvas.WIDTH - 50), r.nextInt(MyCanvas.HEIGHT - 50), ID.FastEnemy, handler));
			}else if(hud.getLevel() == 7) {
				handler.addObject(new BadguyFast(r.nextInt(MyCanvas.WIDTH - 50), r.nextInt(MyCanvas.HEIGHT - 50), ID.FastEnemy, handler));
			}else if(hud.getLevel() == 8) {
				handler.clearEnemys();
				handler.addObject(new BadguyBoss((MyCanvas.WIDTH / 2) -48, -162, ID.EnemyBoss, handler));
			
				
				//handler.addObject(new BadguyBoss((MyCanvas.WIDTH / 2) - 48, -120, ID.EnemyBoss, handler));
			}else if(hud.getLevel() == 9) {
			
				handler.addObject(new Hp(r.nextInt(MyCanvas.WIDTH - 50), r.nextInt(MyCanvas.HEIGHT - 50), ID.Hp, handler));
				//handler.addObject(new BadguyBoss((MyCanvas.WIDTH / 2) - 48, -120, ID.EnemyBoss, handler));
			}else if(hud.getLevel() == 11) {
				handler.clearEnemys();
				//handler.addObject(new BadguyBoss((MyCanvas.WIDTH / 2) - 48, -120, ID.EnemyBoss, handler));
				handler.addObject(new BadguyBasic(r.nextInt(MyCanvas.WIDTH - 50), r.nextInt(MyCanvas.HEIGHT - 50), ID.BasicEnemy, handler));
				handler.addObject(new BadguyBasic(r.nextInt(MyCanvas.WIDTH - 50), r.nextInt(MyCanvas.HEIGHT - 50), ID.BasicEnemy, handler));
				handler.addObject(new BadguyBasic(r.nextInt(MyCanvas.WIDTH - 50), r.nextInt(MyCanvas.HEIGHT - 50), ID.BasicEnemy, handler));
				handler.addObject(new BadguyFast(r.nextInt(MyCanvas.WIDTH - 50), r.nextInt(MyCanvas.HEIGHT - 50), ID.FastEnemy, handler));
	}else if(hud.getLevel() == 13) {
		handler.clearEnemys();
		handler.addObject(new BadguyBoss2((MyCanvas.WIDTH / 2) -48, -162, ID.EnemyBoss, handler));
		handler.addObject(new BadguyBoss3( 480, 430, ID.Ha, handler));
	}else if(hud.getLevel() == 14) {
			handler.addObject(new BadguyBasic(r.nextInt(MyCanvas.WIDTH - 50), r.nextInt(MyCanvas.HEIGHT - 50), ID.BasicEnemy, handler));
			handler.addObject(new BadguyBasic(r.nextInt(MyCanvas.WIDTH - 50), r.nextInt(MyCanvas.HEIGHT - 50), ID.BasicEnemy, handler));
			handler.addObject(new BadguyBasic(r.nextInt(MyCanvas.WIDTH - 50), r.nextInt(MyCanvas.HEIGHT - 50), ID.BasicEnemy, handler));
			handler.addObject(new BadguyFast(r.nextInt(MyCanvas.WIDTH - 50), r.nextInt(MyCanvas.HEIGHT - 50), ID.FastEnemy, handler));
			handler.addObject(new BadguyFast(r.nextInt(MyCanvas.WIDTH - 50), r.nextInt(MyCanvas.HEIGHT - 50), ID.FastEnemy, handler));
			handler.addObject(new BadguyFast(r.nextInt(MyCanvas.WIDTH - 50), r.nextInt(MyCanvas.HEIGHT - 50), ID.FastEnemy, handler));
			handler.addObject(new SmartEnemy(r.nextInt(MyCanvas.WIDTH - 50), r.nextInt(MyCanvas.HEIGHT - 50), ID.SmartEnemy, handler));
			handler.addObject(new SmartEnemy(r.nextInt(MyCanvas.WIDTH - 50), r.nextInt(MyCanvas.HEIGHT - 50), ID.SmartEnemy, handler));
	
	//handler.addObject(new BadguyBoss((MyCanvas.WIDTH / 2) - 48, -120, ID.EnemyBoss, handler));
	}
			
}
}


	public void render(Graphics g) {
	//g.setColor(Color.red);
		
	
		}
}

