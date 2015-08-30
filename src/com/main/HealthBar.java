package com.main;

import static org.lwjgl.opengl.GL11.*;

public class HealthBar {

	private int maxHealth;
	private int currentHealth;
	private float maxSize;
	private float x;
	private float y;
	private float dx;
	private float dy;
	private Player p1;
	
	private float[] colour;
	
	public HealthBar(float x, float y,float maxSize, Player p1){
		this.p1 = p1;
		this.maxHealth = p1.health;
		this.x = x;
		this.y = y;
		this.maxSize = maxSize;
		dy = 10;
		dx = maxSize;
		this.colour = new float[]{0,1,0};
	}
	
	public float calcHP(int health){
		//the percantage of health in decimal;
		float decimal = (float) health/maxHealth;
		return decimal * maxSize;
	}
	
	public void update(){
		this.currentHealth = p1.health;
		dx = calcHP(currentHealth);
		
		if(currentHealth > (maxHealth/2) + (maxHealth/8)){
			this.colour[0] = 0;
			this.colour[1] = 1;
			this.colour[2] = 0;
		}else if(currentHealth < (maxHealth/2) + (maxHealth/8) && currentHealth > (maxHealth/2)-(maxHealth/4)){
			this.colour[0] = 1;
			this.colour[1] = 1;
			this.colour[2] = 0;
		}else{
			this.colour[0] = 1;
			this.colour[1] = 0;
			this.colour[2] = 0;
		}
	}
	
	//disregards the Mesh class, probs bad OOP but is necessary as it needs to render things slightly differently
	public void draw(){
		glDisable(GL_TEXTURE_2D);
		glPushMatrix();
		glTranslatef(x,y,0);
		
		glBegin(GL_QUADS);
		
		glColor3f(colour[0],colour[1],colour[2]);
			glVertex2f(0,0);
			glVertex2f(0,dy);
			glVertex2f(dx,dy);			
			glVertex2f(dx,0);
		
			//stops all the textures taking on the tinge of colour
			glColor3f(1,1,1);
		glEnd();
		glPopMatrix();
		glEnable(GL_TEXTURE_2D);
	}
	
}
