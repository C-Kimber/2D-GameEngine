package dev.kimber.tilegame.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	
	private static final int width = 32, height = 32;
	
	public static BufferedImage stone,sand,grass,dirt, sandstone, tree, wood;
	public static BufferedImage[] player_down, player_up, player_left, player_right, btn_start;
	
	public static void init(){
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));
		//SpriteSheet sheet2 = new SpriteSheet(ImageLoader.loadImage("/textures/sheet2.png"));
        SpriteSheet sheet3 = new SpriteSheet(ImageLoader.loadImage("/textures/sheet3.png"));
		SpriteSheet treeSheet = new SpriteSheet(ImageLoader.loadImage("/textures/tree_alpha.png") );
		SpriteSheet treeSheet2 = new SpriteSheet(ImageLoader.loadImage("/textures/tree_sheet.png") );
		
        
		btn_start = new BufferedImage[2];
		
		btn_start[0] = sheet3.crop(96, 0, 96, 96);
		btn_start[1] = sheet3.crop(96*2, 0, 96, 96);
		
		player_down = new BufferedImage[2];
		player_up = new BufferedImage[2];
		player_left = new BufferedImage[2];
		player_right = new BufferedImage[2];
		
		player_down[0] = sheet.crop(0, height * 2, width, height);
		player_down[1] = sheet.crop(width, height * 2, width, height);
		player_up[0] = sheet.crop(0, 0, width, height);
		player_up[1] = sheet.crop(width, 0, width, height);
		player_left[0] = sheet.crop(0, height, width, height);
		player_left[1] = sheet.crop(width, height, width, height);
		player_right[0] = sheet.crop(0, height * 3, width, height);
		player_right[1] = sheet.crop(width, height * 3, width, height);
		
		//Static Images
		wood = treeSheet2.crop(512-64, 96*2, 64,32);
		tree = treeSheet.crop(0, 0, width*8, height*10);
		stone =   sheet3.crop(9*32,32,32, 32);
		//sand =  sheet2.crop(0, 64, 64, 64);
	    grass =  sheet3.crop(32, 32, 32, 32);
	    //dirt =  sheet2.crop(0, 64*3, 64, 64);
	   // sandstone =  sheet2.crop(0, 64*4, 64, 64);
	}

}
