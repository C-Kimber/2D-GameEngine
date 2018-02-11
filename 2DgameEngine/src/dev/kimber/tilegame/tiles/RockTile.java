package dev.kimber.tilegame.tiles;

import java.awt.image.BufferedImage;

import dev.kimber.tilegame.gfx.Assets;

public class RockTile extends Tile{

	public RockTile(int id) {
		super(Assets.stone, id);
	}
	
	//@overrides
	public boolean isSolid(){
		return true;
	}

}
