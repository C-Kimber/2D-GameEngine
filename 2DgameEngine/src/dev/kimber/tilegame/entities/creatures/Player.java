package dev.kimber.tilegame.entities.creatures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import dev.kimber.tilegame.Game;
import dev.kimber.tilegame.Handler;
import dev.kimber.tilegame.entities.Entity;
import dev.kimber.tilegame.gfx.Animation;
import dev.kimber.tilegame.gfx.Assets;
import dev.kimber.tilegame.inventory.Inventory;

public class Player extends Creature{
	
	private Animation animDown, animUp, animRight, animLeft;
	// Attack Timer
	private long lastAttackTimer, attackCooldown = 800,attackTimer = attackCooldown;
	//Inventory
	private Inventory inventory;
	
	
	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFUALT_CREATURE_WIDTH, 
				Creature.DEFUALT_CREATURE_HEIGHT);
		bounds.x = 16;
		bounds.y = 32;
		bounds.width =32;
		bounds.height = 32;
		
		//Animations
		animDown = new Animation(250,Assets.player_down);
		animUp = new Animation(250,Assets.player_up);
		animRight = new Animation(250,Assets.player_right);
		animLeft= new Animation(250,Assets.player_left);

		inventory = new Inventory(handler);
	}

	public void tick() {
		//Animations
		animDown.tick();
		animUp.tick();
		animRight.tick();
		animLeft.tick();
		//Movement
		getInput();
		move();
		handler.getGameCamera().centerOnEntity(this);
		//Attack
		checkAttacks();
		inventory.tick();
	}
	
	private void checkAttacks(){
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		if(attackTimer <  attackCooldown){
			return;
		}
		
		Rectangle cb = getCollisionBounds(0,0);
		Rectangle ar = new Rectangle();
		int arSize = 20;
		ar.width = arSize;
		ar.height = arSize;
		
		if(handler.getKeyManager().aUp){
			ar.x = cb.x+ cb.width / 2 - arSize / 2;
			ar.y = cb.y - arSize;
		}else if(handler.getKeyManager().aDown){
			ar.x = cb.x+ cb.width / 2 - arSize / 2;
			ar.y = cb.y + cb.height;
		}else if(handler.getKeyManager().aLeft){
			ar.x = cb.x- arSize;
			ar.y = cb.y + cb.height/2 - arSize /2;
		}else if(handler.getKeyManager().aRight){
			ar.x = cb.x+ cb.width;
			ar.y = cb.y + cb.height/2 - arSize /2;
		}else{
			return;
		}
		
		attackTimer = 0;
		
		for(Entity e : handler.getWorld().getEntityManager().getEntities()){
			if(e.equals(this))
				continue;
			if(e.getCollisionBounds(0, 0).intersects(ar) ) 
				e.hurt(1);
				return;
		}
		
	}
	
	public void die(){
		System.out.println("You LOSE");
	}
	
	private void getInput(){
		xMove = 0;
		yMove = 0;
		
		if(handler.getKeyManager().up)
			yMove = -speed;
		if(handler.getKeyManager().down)
			yMove = speed;
		if(handler.getKeyManager().left)
			xMove = -speed;
		if(handler.getKeyManager().right)
			xMove = speed;
	}

	public void render(Graphics g) {
		
		inventory.render(g);
		
		g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()),
				(int) (y - handler.getGameCamera().getyOffset()),
				width, height, null);
		
		/*g.setColor(Color.red);
		g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
				(int) (y + bounds.y - handler.getGameCamera().getyOffset()),
				bounds.width,
				bounds.height);*/
	}
	
	private BufferedImage getCurrentAnimationFrame(){
			if(xMove < 0){
				return animLeft.getCurrentFrame();
			}else if(xMove > 0){
				return animRight.getCurrentFrame();
			}else if(yMove < 0 ){
				return animUp.getCurrentFrame();
			}else{
				return animDown.getCurrentFrame();
			}
	}
	
	//GETTERS SETTERS

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	

}
