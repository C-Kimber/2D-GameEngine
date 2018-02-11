package dev.kimber.tilegame.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import dev.kimber.tilegame.ui.UIManager;

public class MouseManager implements MouseListener, MouseMotionListener{

	private boolean leftPressed, rightPressed;
	private int mouseX, mouseY;
	private UIManager uiManager;
	
	public void MouseManager(){
		
	}
	
	//Getters and Setters
	
	public void setUIManager(UIManager uiManager){
		this.uiManager = uiManager;
	}
	
	public boolean isLeftPressed(){
		//System.out.print(leftPressed);
		return leftPressed;
	}
	
	public boolean isRightPressed(){
		//System.out.println(rightPressed);
		return rightPressed;
	}
	
	public int getMouseX(){
		return mouseX;
	}
	
	public int getMouseY(){
		return mouseY;
	}
	
	//Implemented methods
	
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		
		if(uiManager != null)
			uiManager.onMouseMoved(e);
		
	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent e) {
		//B1 - Left, B2 - Middle, M3 - Right
		if(e.getButton() == MouseEvent.BUTTON1)
			leftPressed = true;
		if(e.getButton() == MouseEvent.BUTTON3)
			rightPressed = true;
		
	}

	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1)
			leftPressed = false;
		if(e.getButton() == MouseEvent.BUTTON2)
			rightPressed = false;
		
		if(uiManager != null)
			uiManager.onMouseRelease(e);
		
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
