package com.main;

public class Score {

	private float x;
	private float y;
	private float dx;
	private float dy;
	private int score;
	
	
	private int ones;
	private int tens;
	private int hundreds;
	
	public Score(float x, float y){
		this.x = x;
		this.y = y;
		this.score = 0;
		ones = 0;
		tens = 0;
		hundreds = 0;
	}
	
	public void incScore(int inc){
		this.score += inc;
		ones += inc;
		if(ones >= 10){
			ones %= 10;
			tens ++;
		}
		if(tens >= 10){
			tens %= 10;
			hundreds++;
		}
	}
	
	public int getScore(){
		System.out.println(" hundreds: " + this.hundreds + " tens: " + this.tens + "ones: " + this.ones );
		return this.score;
	}
	
	public static void init(){
		
	}
	
	public void update(){
		
	}
	
	public void draw(){
		
		//if it has tens
		if(score/10 >= 1){
		Mesh.draw(x, y, dx, dy, 0);
		}
	}
	
}
