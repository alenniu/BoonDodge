package Main;
//these are the imports
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.print.DocFlavor.URL;
import javax.swing.ImageIcon;

import Main.Goodguy;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class MyCanvas extends Canvas implements Runnable{
	
	
	
	private static final long serialVersionUID = 1550691097823471818L;
//This sets all WIDTH values to 640 and HEIGHT values to width / 12*9
	//declaring variable WIDTH and HEIGHT to be 
	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
	
	private Thread thread;
	private boolean running = false;
	
	private BufferedImage image;
	private BufferedImage image2;
	
	private Random r;
	private Handler handler;
	private HUD hud;
	private Spawn spawner;
	private Menu menu;
	
	//these are the enumerals for the states of the game
	public enum STATE {
		Menu,
		Help,
		Game,
		Skin1,
		End
	};

	//setting the state of the game on open to be Menu state. connects to the Menu class
	public static STATE gameState = STATE.Menu;
	
	//create new game window
	public MyCanvas() {
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/kBjvAi.jpg"));
			image2 = ImageIO.read(getClass().getResourceAsStream("/level 1-5.jpeg"));
		} catch(IOException e) {
			e.printStackTrace();
		}
		handler = new Handler(); //instantiate the constructors
		hud = new HUD();
		menu = new Menu(this, handler, hud);
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(menu);
		
		new MyScreen(WIDTH, HEIGHT, "Boon Dodge", this); //calling an instance of MyScreen
		

		spawner = new Spawn(handler, hud);

		r = new Random();
		
		
		if(gameState == STATE.Game)//if the state is on the game stage
		{
			//handler.addObject(new Player2(WIDTH/2+64, HEIGHT/2-32, ID.Player2, handler));
			handler.addObject(new Goodguy(WIDTH/2-32, HEIGHT/2-32, ID.Player, handler)); // adding a new object
			
			handler.addObject(new BadguyBasic(r.nextInt(MyCanvas.WIDTH - 50), r.nextInt(MyCanvas.HEIGHT - 50), ID.BasicEnemy, handler));
		}else {
			//this is the menu particles
			/*for(int i = 0; i < 20; i++) {
				handler.addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticle, handler));
			}*/
		}
		//repaint();
	//	for(int i = 0; i < 50; i++) {
			

		/*handler.addObject(new BasicEnemy(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.BasicEnemy, handler));
		handler.addObject(new BasicEnemy(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.BasicEnemy, handler));
		handler.addObject(new BasicEnemy(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.BasicEnemy, handler));
		handler.addObject(new BasicEnemy(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.BasicEnemy, handler));
		handler.addObject(new BasicEnemy(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.BasicEnemy, handler));*/
			//handler.addObject(new Player(WIDTH/2+64, HEIGHT/2-32, ID.Player2, handler));
		//}
		//this adds the info from the Player value in ID.java
		playIt("files/zero.wav");
		
	} 
	//the music and sound effects
	public void playIt(String filename) {
		
		try {
			InputStream in = new FileInputStream(filename);
			AudioStream as = new AudioStream(in);
			AudioPlayer.player.start(as);
		} catch (IOException e) {
			System.out.println(e);
		}

		}
	//If the game is started, start a new thread
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	//If the game is stopped, turn running into false, end game
	//try and catch is like if statements
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	//this is a fps formula
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			if(running)
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
		
	}

	private void tick() {
		handler.tick();
		if(gameState == STATE.Game)
		{
			hud.tick();
			spawner.tick();
		
			if(HUD.HEALTH <= 0) {
				HUD.HEALTH = 100;
				gameState = STATE.End;
				handler.clearEnemys();
				for(int i = 0; i < 20; i++) {
					//handler.addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticle, handler));
				}

			}
		}else if(gameState == STATE.Menu || gameState == STATE.End) {
			menu.tick();
		}

	}

	//this is the render, buffer strategy
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		// this starts the game graphics
		Graphics g = bs.getDrawGraphics();
		
		
		//java.awt.Graphics commands
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.drawImage(image, 0, 0, WIDTH, HEIGHT, null);
	//	g.setColor(Color.black); //color of the screen
		
		// fill the rectangle with the screen size WIDTH HEIGHT
		
		handler.render(g);
		
		if(gameState == STATE.Game)
		{
			//dg.drawImage(image2, 0, 0, WIDTH, HEIGHT, null);
			hud.render(g);
			
		}else if(gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End) {
			menu.render(g);
		}
		
		
		
		g.dispose();
		bs.show();
	} //main method to run Game
	
	public static float clamp(float var, float min, float max) {
		if(var >= max)
			return var = max;
		else if(var <= min)
			return var = min;
		else
			return var;
	}
	
	public static void main(String args []) {
		new MyCanvas();
	
	}
}
