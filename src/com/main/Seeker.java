package com.main;

import org.lwjgl.opengl.GL11;

public class Seeker extends Enemy{

	Player player;
	
	public Seeker(float x, float y, Player player) {
		super(x, y, 0);
		this.player = player;
		this.seeker = true;
	}
	
	@Override
	public void update(){
		float py = player.y;
		float px = player.x;
		
		this.rz = (float) Math.toDegrees(Math.atan2(py - this.y, px - this.x));
		
		this.rz += 180;
		
		
		//float hyp = (float) Math.sqrt(Math.pow(py - this.y, 2) + Math.pow(px - this.x, 2));
		
//		if(hyp < 100 && hyp > 50){
//			speed = 2;
//		}else if(hyp < 50 && hyp > 25){
//			speed = 3;
//		}else if(hyp < 25){
//			speed = 4;
//		}else{
//			speed = 1;
//		}
		
		speed += 0.01f;
		if(speed > 10f){
			speed = 10f;
		}
		//TODO closer to player the faster it goes
		this.counter = 0;
		super.update();
	}

	 int deathTimer = 0;
	@Override
	public void kill(){
		super.kill();
		Game.moreSeeker = true;
	}
	
	@Override
	public void draw(){
		//change colour to atinge fo red
		GL11.glColor3f(0.9f, 0.1f, 0.1f);
		super.draw();
		GL11.glColor3f(1, 1, 1);
		
	}
	
}
