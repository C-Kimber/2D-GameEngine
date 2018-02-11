package dev.kimber.tilegame.states;

import java.awt.Color;
import java.awt.Graphics;

import dev.kimber.tilegame.Game;
import dev.kimber.tilegame.Handler;
import dev.kimber.tilegame.gfx.Assets;
import dev.kimber.tilegame.ui.ClickListener;
import dev.kimber.tilegame.ui.UIImageButton;
import dev.kimber.tilegame.ui.UIManager;

public class MenuState extends State {
	
	private UIManager uiManager;
	
	public MenuState(final Handler handler){
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
		
		uiManager.addObject(new UIImageButton(200, 200, 128, 128, Assets.btn_start, new ClickListener(){

			public void onClick() {
				handler.getMouseManager().setUIManager(null);
				State.setState(handler.getGame().gameState);
				
				
			}}));
	}
	
	public void tick(){
		if(handler.getMouseManager().isLeftPressed() && handler.getMouseManager().isRightPressed()){
			State.setState(handler.getGame().gameState);
		}
		uiManager.tick();
			
	}
	

	public void render(Graphics g) {
		uiManager.render(g);
		
	}

}