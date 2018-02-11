package dev.kimber.tilegame.entities.statics;

import java.awt.Color;
import java.awt.Graphics;

import dev.kimber.tilegame.Handler;
import dev.kimber.tilegame.gfx.Assets;
import dev.kimber.tilegame.items.Item;
import dev.kimber.tilegame.tiles.Tile;

public class Tree extends StaticEntity{

	public Tree(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, (int)(Tile.TILEWIDTH * 2), Tile.TILEHEIGHT*3);
		
		bounds.x = 54;
		bounds.y = 130;
		bounds.width = 22;
		bounds.height =40;
	}

	public void tick() {
		// TODO Auto-generated method stub
		
	}
	
	public void die(){
		for(int i= 0; i < (int) (Math.random() * 3)+1; i++){
			handler.getWorld().getItemManager().addItem(Item.woodItem.createNew((int) (x + bounds.x + i * 4), (int) (y+bounds.y + i * 4)));
		}
		active = false;
	}

	public void render(Graphics g) {
		g.drawImage(Assets.tree, (int) (x - handler.getGameCamera().getxOffset()),
				(int) (y - handler.getGameCamera().getyOffset() ), width, height, null);
		//renderCollisionBox(g);
		}

}
