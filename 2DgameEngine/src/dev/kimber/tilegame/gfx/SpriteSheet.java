package dev.kimber.tilegame.gfx;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	
	private BufferedImage sheet;
	
	public SpriteSheet(BufferedImage sheet){
		this.sheet = sheet;
		
	}
	
	public BufferedImage crop(int x, int y, int width, int height){
		//System.out.println(sheet.getAlphaRaster());
		return sheet.getSubimage(x, y, width, height);
		
	}

}
