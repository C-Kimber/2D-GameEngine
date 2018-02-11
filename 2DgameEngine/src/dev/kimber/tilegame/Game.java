package dev.kimber.tilegame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;


import dev.kimber.tilegame.gfx.Assets;
import dev.kimber.tilegame.gfx.GameCamera;
import dev.kimber.tilegame.gfx.ImageLoader;
import dev.kimber.tilegame.gfx.SpriteSheet;
import dev.kimber.tilegame.input.KeyManager;
import dev.kimber.tilegame.input.MouseManager;
import dev.kimber.tilegame.states.GameState;
import dev.kimber.tilegame.states.MenuState;
import dev.kimber.tilegame.states.State;

public class Game implements Runnable{
	
	private Display display;
	private Thread thread;
	private boolean running = false;
	private BufferStrategy bs;
	private Graphics g;
	
	private int width, height;
	public String title;
	
	//States
	public State gameState;
	public State menuState;
	
	//Camera
	private GameCamera gameCamera;
	
	//Input
	
	private KeyManager keyManager;
	private MouseManager mouseManager;
	
	//Handler
	
    private Handler handler;
	
	
	public Game(String title, int width, int height){
		this.width = width;
		this.height = height;
		this.title = title;
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
		
	}
	
	private void init(){
	
		display = new Display(title, width, height);
		display.getJFrame().addKeyListener(keyManager);
		display.getJFrame().addMouseListener(mouseManager);
		display.getJFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		Assets.init();
		
	    handler = new Handler(this);

		gameCamera = new GameCamera(handler, 0, 0);
		
		gameState = new GameState(handler);
		menuState = new MenuState(handler);
		State.setState(gameState);
	}
	
	private void tick(){
		keyManager.tick();
		
		if(State.getState() != null){
			State.getState().tick();
			
		}
		
	}
	
	private void render(){
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null){
			
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		
		g = bs.getDrawGraphics();
		//clear
		g.clearRect(0, 0, width, height);
		//draw here
		
		if(State.getState() != null){
			State.getState().render(g);
			
		}
		
		//end draw
		bs.show();
		g.dispose();
	}
	
	public void run(){
		
		init();
		
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while(running){
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer +=now - lastTime;
			lastTime = now;
			
			if(delta >= 1){
			tick();
			render();
			delta--;
			ticks++;
			}
			if(timer >= 1000000000){
				//System.out.println("Frames: "+ticks);
				ticks = 0;
				timer = 0;
			}
		}
		stop();
	}
	
	public KeyManager getKeyManager(){
		return keyManager;
	}
	
	public MouseManager getMouseManager(){
		return mouseManager;
	}
	
	public GameCamera getGameCamera(){
		return gameCamera;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public synchronized void start(){
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
		
	}
	
	public synchronized void stop(){
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
}
