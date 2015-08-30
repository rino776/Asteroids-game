package com.main;

import org.lwjgl.input.Keyboard;

public class Player extends GameObject {

	boolean forward = false;

	public int health;
	Texture tex;
	//bullets per second
	public float BPS;
	public Player(float x, float y) {
		super(x, y, 20, 28, 90);
		this.xAmt = 0;
		this.yAmt = 0;
		this.speed = 0;
		this.rz = 0;
		this.health = 100;
		this.tex = Game.shipNoFire;
		this.BPS = 0.1f;
	}
	
public void move(boolean slows){
	
		float angle = (float) Math.toRadians(Math.abs(rz));
		float forwardVector = speed;
	
		
		xAmt = (float) (forwardVector * Math.sin(angle));
		yAmt =  (float)(forwardVector * -Math.cos(angle));
		
		moveX(xAmt);
		moveY(yAmt);
		Main.soundSystem.setListenerVelocity(xAmt, yAmt, 0);
		
	if(slows){
		if(speed != 0){
			if(speed <= 0.9f){
				speed *= 0.8;
			}else if(speed <= 0.5){
				speed *= 0.9;
			}else if(speed <= 0.3){
				speed *=0.99f;
			}else{
				speed *= 0.95;
			}
		}
		}
	}
	

private boolean canShoot = false;
private boolean tryShoot = false;
private int counter = 0;
	@Override
	public void update(){
		float rotSpeed = 5;
		
		if(Keyboard.isKeyDown(Keyboard.KEY_UP)){
			speed += 1;
			forward = true;
		}else{
			forward = false;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
			rotateZ(rotSpeed);
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
			rotateZ(-rotSpeed);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
			tryShoot = true;
		}
		
		//TODO decide if I want this, or if I want to have to press a button
		
		if(canShoot){
			new Bullet(this.getX(), this.getY(), this.getRz(), this.speed);
			canShoot = false;
		}else{
			counter ++;
		}
		
		if(counter >= BPS * 60){
			if(tryShoot){
				canShoot = true;
				tryShoot = false;
			}
			counter = 0;
		}
		move(true);
		
		Main.soundSystem.setListenerPosition(x, y, 0);
		Main.soundSystem.setListenerAngle(rz);
		
	}
	
	
	@Override
	public void draw(){
		if(forward){
			this.tex = Game.shipFire;
		}else{
			this.tex = Game.shipNoFire;
		}
		Mesh.draw(this.x, this.y, this.dx, this.dy, this.rz, this.tex);	
		
	}

	public void damage(int amt) {
		health -= amt;
		System.out.println("health: " + health);
		
	}

}
