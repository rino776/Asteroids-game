package com.main;

import java.awt.Rectangle;

public class GameObject {

	float x,y,dx,dy,rz;
	float xAmt;
	float yAmt;
	float speed;
	
	
	public GameObject(float x, float y, float dx, float dy, float rz){
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
		this.rz = rz;
	
	}
	
	public void moveX(float amt){
		this.x += amt;
		
	}
	
	public void moveY(float amt){
		this.y += amt;
	}
	
	public void rotateZ(float amt){
		this.rz += amt;
		if(rz >= 360){
			rz -= 360;
		}else if(rz <= 360){
			rz += 360;
		}
	}
	

	
	public void update(){
		
	}
	
	public void draw(){
	
		Mesh.draw(this.x, this.y, this.dx, this.dy, this.rz);
	}
	
	public float getX(){
		return this.x;
	}
	
	public float getY(){
		return this.y;
	}
	
	public float getRz(){
		return this.rz;
	}
	
	public boolean collides(GameObject other){
		Rectangle me = new Rectangle();
		Rectangle him = new Rectangle();
		me.setBounds((int)(this.x - this.dx/2),(int) (this.y- this.dy/2),(int) this.dx,(int) this.dy);
		him.setBounds((int)(other.x - other.dx/2), (int)(other.y - other.dy/2), (int)other.dx, (int)other.dy);
		return him.intersects(me) || me.intersects(him);
	}
}
