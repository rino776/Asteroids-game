package com.main;

public class Score {

	private float x;
	private float y;
	private float dx;
	private float dy;
	private int score;
	
	Texture texO;
	Texture texT;
	Texture texH;
	
	Texture zero;
	Texture one;
	Texture two;
	Texture three;
	Texture four;
	Texture five;
	Texture six;
	Texture seven;
	Texture eight;
	Texture nine;
	
	private int ones;
	private int tens;
	private int hundreds;
	
	public Score(float x, float y){
		this.x = x;
		this.y = y;
		this.dx = 50;
		this.dy = 50;
		this.score = 0;
		ones = 0;
		tens = 0;
		hundreds = 0;
		
		 zero = Texture.loadTexture("assets/numbers/0.png");
		 one= Texture.loadTexture("assets/numbers/1.png");
		 two= Texture.loadTexture("assets/numbers/2.png");
		 three= Texture.loadTexture("assets/numbers/3.png");
		 four= Texture.loadTexture("assets/numbers/4.png");
		 five= Texture.loadTexture("assets/numbers/5.png");
		 six= Texture.loadTexture("assets/numbers/6.png");
		 seven= Texture.loadTexture("assets/numbers/7.png");
		 eight= Texture.loadTexture("assets/numbers/8.png");
		 nine= Texture.loadTexture("assets/numbers/9.png");
		
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
		
		if(ones == 1){
			texO = one;
		}else if(ones == 2){
			texO = two;
		}else if(ones == 3){
			texO = three;
		}else if(ones == 4){
			texO = four;
		}else if(ones == 5){
			texO = five;
		}else if(ones == 6){
			texO = six;
		}else if(ones == 7){
			texO = seven;
		}else if(ones == 8){
			texO = eight;
		}else if(ones == 9){
			texO = nine;
		}else if(ones == 0){
			texO = zero;
		}
		
		Mesh.draw(x, y, dx, dy, 180,texO);
	
		if(tens == 1){
			texT = one;
		}else if(tens == 2){
			texT = two;
		}else if(tens == 3){
			texT = three;
		}else if(tens == 4){
			texT = four;
		}else if(tens == 5){
			texT = five;
		}else if(tens == 6){
			texT = six;
		}else if(tens == 7){
			texT = seven;
		}else if(tens == 8){
			texT = eight;
		}else if(tens == 9){
			texT = nine;
		}else if(tens == 0){
			texT = zero;
		}
		Mesh.draw(x - dx -1, y, dx, dy, 180,texT);
	
		if(hundreds == 1){
			texH = one;
		}else if(hundreds == 2){
			texH = two;
		}else if(hundreds == 3){
			texH = three;
		}else if(hundreds == 4){
			texH = four;
		}else if(hundreds == 5){
			texH = five;
		}else if(hundreds == 6){
			texH = six;
		}else if(hundreds == 7){
			texH = seven;
		}else if(hundreds == 8){
			texH = eight;
		}else if(hundreds == 9){
			texH = nine;
		}else if(hundreds == 0){
			texH = zero;
		}
		Mesh.draw((x-2)-2*dx, y, dx, dy, 0,texH);
		
	}
	
}
