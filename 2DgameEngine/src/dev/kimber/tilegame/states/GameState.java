package dev.kimber.tilegame.states;

import java.awt.Graphics;

import dev.kimber.tilegame.Game;
import dev.kimber.tilegame.Handler;
import dev.kimber.tilegame.entities.creatures.Player;
import dev.kimber.tilegame.entities.statics.Tree;
import dev.kimber.tilegame.gfx.Assets;
import dev.kimber.tilegame.tiles.Tile;
import dev.kimber.tilegame.worlds.World;

public class GameState extends State {
	
	private World world;
	
	public GameState(Handler handler){
		super(handler);
		world = new World(handler, "res/worlds/w2.txt");
		handler.setWorld(world);
	} 
	
	public void tick(){
		world.tick();

	}
	

	public void render(Graphics g) {
		world.render(g);

	}

}
