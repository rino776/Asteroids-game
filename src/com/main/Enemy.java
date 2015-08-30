package com.main;

/**
 * 
 * @author rharrold
 *
 *	TODO: balancing, should I have multiple difficulties? 
 *			should I make it super easy? super hard?
 */
public class Enemy extends GameObject{

	boolean seeker;
	boolean alive;
	protected int counter;
	Texture tex;
	
	public Enemy(float x, float y, float rz) {
		super(x, y, 50, 50, rz);
		alive = true;
		this.speed = 1;
		counter = 0;
		Game.Enemies.add(this);
		this.tex = Game.asteriod;
		this.seeker = false;
	}
	
	public void kill(){
		alive = false;
		Game.dead.add(this);
		
		
	//should alternate the sources so that we can get overlapping sounds	
		if(Main.soundSystem.playing("explosion2")){
			Main.soundSystem.stop("explosion");
			Main.soundSystem.setPosition("explosion", this.x, this.y, 0);
			Main.soundSystem.play("explosion");
		}else if(Main.soundSystem.playing("explosion")){
			Main.soundSystem.stop("explosion1");
			Main.soundSystem.setPosition("explosion1", this.x, this.y, 0);
			Main.soundSystem.play("explosion1");
		}else{
			Main.soundSystem.stop("explosion2");
			Main.soundSystem.setPosition("explosion2",this.x, this.y, 0);
			Main.soundSystem.play("explosion2");
		}
	}
	
	@Override
	public void update(){
		//only do everything if it is alive
		if(alive){
			float xAmt = 0;
			float yAmt = 0;
			double angle = Math.toRadians(getRz());
			
			yAmt = (float) (speed * -Math.cos(angle - Math.toRadians(90)));
			xAmt = (float) (speed * Math.sin(angle - Math.toRadians(90)));
			
			this.moveX(xAmt);
			this.moveY(yAmt);
			
			if(counter > 10 * 60){
				this.kill();
				counter = 0;
				Game.score.incScore(-1);
			}
			counter++;
		}
	}
	@Override
	public void draw(){
		if(alive)
			Mesh.draw(x, y, dy, dy, rz, tex);
	}

}
