package dev.kimber.tilegame.entities.statics;

import dev.kimber.tilegame.Handler;
import dev.kimber.tilegame.entities.Entity;

public abstract class StaticEntity extends Entity {
	
	public StaticEntity(Handler handler, float x, float y, int width, int height){
		super(handler, x, y, width,height);
	}
	

}
