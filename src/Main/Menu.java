package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import Main.MyCanvas.STATE;

public class Menu extends MouseAdapter{
	
	
	Image img;
	private MyCanvas game;
	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	
	public Menu(MyCanvas game, Handler handler, HUD hud) {
		this.game = game;
		this.hud = hud;
		this.handler = handler;
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if(game.gameState == STATE.Menu) {
			//play button
			if(mouseOver(mx, my, 210, 150, 200, 64)) {
				game.gameState = STATE.Game;
			
				
			//	handler.addObject(new Player2(MyCanvas.WIDTH/2-32, MyCanvas.HEIGHT/2-32, ID.Player2, handler));
				handler.addObject(new Goodguy(MyCanvas.WIDTH/2-32, MyCanvas.HEIGHT/2-32, ID.Player, handler));
				//handler.clearEnemys();
				handler.addObject(new BadguyBasic(r.nextInt(MyCanvas.WIDTH - 50), r.nextInt(MyCanvas.HEIGHT - 50), ID.BasicEnemy, handler));
				
			}
			
			//help button
			if(mouseOver(mx, my, 210, 250, 200, 64)) {
				game.gameState = STATE.Help;
			}
			
			//2 player
			 if(mouseOver(mx, my, 15, 390, 50, 42)) {
				 handler.addObject(new Player2(MyCanvas.WIDTH/2-32, MyCanvas.HEIGHT/2-32, ID.Player2, handler));
				 
				 //skin 1
			}
			 if(mouseOver(mx, my, 100, 390, 50, 42)) {
				 game.gameState = STATE.Game;
				 handler.addObject(new Goodguy(MyCanvas.WIDTH/2-32, MyCanvas.HEIGHT/2-32, ID.Skin1, handler));
					//handler.clearEnemys();
					handler.addObject(new BadguyBasic(r.nextInt(MyCanvas.WIDTH - 50), r.nextInt(MyCanvas.HEIGHT - 50), ID.BasicEnemy, handler));
					
			}
			 
		
			
			
			//quit button
			if(mouseOver(mx, my, 210, 350, 200, 64)) {
				System.exit(1);
			}
		}
		
	
		
		//back button for help 
		if(game.gameState == STATE.Help) {
			if(mouseOver(mx, my, 210, 350, 200, 64)) {
				game.gameState = STATE.Menu;
				return;
			}
		}
		//back button for help 
		if(game.gameState == STATE.End) {
			if(mouseOver(mx, my, 210, 350, 200, 64)) {
				game.gameState = STATE.Game;
			
				hud.setLevel(1);
				hud.setScore(0);
			//	handler.addObject(new Player2(MyCanvas.WIDTH/2-32, MyCanvas.HEIGHT/2-32, ID.Player2, handler));
				handler.addObject(new Goodguy(MyCanvas.WIDTH/2-32, MyCanvas.HEIGHT/2-32, ID.Player, handler));
				//handler.clearEnemys();
				handler.addObject(new BadguyBasic(r.nextInt(MyCanvas.WIDTH - 50), r.nextInt(MyCanvas.HEIGHT - 50), ID.BasicEnemy, handler));
				

			}if(mouseOver(mx, my, 10, 350, 80, 64)) {
				game.gameState = STATE.Menu;
				hud.setLevel(1);
				hud.setScore(0);
			}
		}
		
	}
	
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
		if(mx > x && mx < x + width) {
			if(my > y && my < y + height) {
				return true;
				}else return false;
			}else return false;
	}
	
	
	public void tick() {
		
	
	}
	
	/* public Menu()
	    {
	        // Loads the background image and stores in img object.
	        img = Toolkit.getDefaultToolkit().createImage("kBjvAi.jpg");
	    }
		g.drawImage(img, 0, 0, null);*/
	
	public void render(Graphics g) {
		if(game.gameState == STATE.Menu) {
			Font fnt = new Font("arial", 1, 70);
			Font fnt2 = new Font("arial", 1, 30);
			Font fnt3 = new Font("arial", 1, 23);
			Font fnt4 = new Font("arial", 1, 12);
			
			
			g.setFont(fnt);
			g.setColor(Color.orange);
			g.drawString("Boon Dodge", 125, 70);
			
			g.setFont(fnt4);
			g.setColor(Color.magenta);
			g.drawString("Code Written by Alen Niu", 480, 430);
			
			g.setColor(Color.green);
			g.setFont(fnt2);
			g.drawRect(210, 150, 200, 64);
			g.drawString("Play", 270, 190);
			
			g.setColor(Color.blue);
			g.drawRect(210, 250, 200, 64);
			g.drawString("Help", 270, 290);
			
			g.setColor(Color.red);
			g.drawRect(210, 350, 200, 64);
			g.drawString("Quit", 270, 390);
			
			g.setColor(Color.black);
			g.setFont(fnt3);
			g.drawRect(15, 390, 50, 42);
			g.drawString("2 Player", 7, 375);
		
			g.setColor(Color.blue);
			g.setFont(fnt3);
			g.drawRect(100, 390, 50, 42);
			g.drawString("Skin1", 100, 375);
			
			
		}else if(game.gameState == STATE.Help) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			Font fnt3 = new Font("arial", 1, 20);
			Font fnt4 = new Font("arial", 1, 12);
			
			g.setFont(fnt);
			g.setColor(Color.orange);
			g.drawString("Help", 240, 70);
			
			g.setFont(fnt3);
			g.setColor(Color.pink);
			g.drawString("Welcome to Boon Dodge, a compelling wave dodge game!", 50, 120);
			g.drawString("The goal of this game is to survive waves with different", 50, 150);
			g.drawString("kinds of enemies including our custom bosses!", 53, 175);
			g.drawString("We have a unique shop and point system that may be", 53, 210);
			g.drawString("used to purchase items or boosts!", 53, 230);
			g.drawString("Avoid being hit by enemies or you will lose", 53, 260);
			g.setColor(Color.green);
			g.drawString("HEALTH", 473, 260);
			g.setColor(Color.red);
			g.drawString("Use WASD keys to move player and dodge enemies", 50, 300);
			g.setFont(fnt4);
			g.setColor(Color.blue);
			g.drawString("Code Written by Alen Niu", 480, 50);
			
			g.setColor(Color.orange);
			g.setFont(fnt2);
			g.drawRect(210, 350, 200, 64);
			g.drawString("Back", 270, 390);
		}else if(game.gameState == STATE.End) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			Font fnt3 = new Font("arial", 1, 20);
			Font fnt4 = new Font("arial", 1, 12);
			
			g.setFont(fnt);
			g.setColor(Color.orange);
			g.drawString("Game Over", 185, 70);
			
			g.setFont(fnt3);
			g.setColor(Color.pink);
			g.drawString("You lost with a score of: ", 180, 120);
			g.drawString("You lost with a level of: ", 190, 150);
			g.drawString(" " + hud.getLevel(), 415, 150);
			g.drawString(" " + hud.getScore(), 415, 120);

			
			g.setColor(Color.orange);
			g.setFont(fnt2);
			g.drawRect(210, 350, 200, 64);
			g.drawString("Try Again", 240, 390);
			
			g.setColor(Color.magenta);
			g.setFont(fnt2);
			g.drawRect(10, 350, 80, 64);
			g.drawString("Back", 13, 390);
			g.setFont(fnt4);
			g.drawString("to menu", 13, 408);
			
		}
	}
}
