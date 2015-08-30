package com.main;

public class Bullet extends GameObject{

	Texture tex;
	boolean alive;
	
	private int counter;
	public Bullet(float x, float y, float rz, float speed) {
		super(x, y, 15, 30, rz);
		Game.bullets.add(this);
		this.speed = 2 + speed;
		tex = Game.bullet;
		alive = true;
		counter = 0;
		Main.soundSystem.stop("laser");
		Main.soundSystem.setPosition("laser", x, y, 0);
		Main.soundSystem.play("laser");
	}
	
	public void kill(){
		alive = false;
	}
	
	@Override
	public void update(){
		if(alive){
			Main.soundSystem.setPosition("laser", x, y, 0);
			float xAmt = 0;
			float yAmt = 0;
			double angle = Math.toRadians(getRz());
			
			xAmt = (float) (speed * Math.cos(angle - Math.toRadians(90)));
			yAmt = (float) (speed * Math.sin(angle - Math.toRadians(90)));
			
			this.moveX(xAmt);
			this.moveY(yAmt);
			speed+= 1;
			
			if(counter > 10 * 60){
				this.kill();
				counter = 0;
			}
			counter ++;
		}
	}
	
	@Override
	public void draw(){
		if(alive)
			Mesh.draw(x, y, dx, dy, rz, tex);
	}

	
	
}
