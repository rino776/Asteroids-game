package com.main;

import java.util.ArrayList;
import java.util.Random;


public class Game {
	
public static ArrayList<Bullet> bullets;
public static ArrayList<Enemy> Enemies;
public static ArrayList<Enemy> dead;

	//textures
	public static Texture bullet;
	public static Texture shipFire;
	public static Texture shipNoFire;
	public static Texture asteriod;
	
	//player(dah)
	static Player p1;
	private static int numEnemy;
	
	//game borders
	private static GameObject left;
	private static GameObject right;
	private static GameObject up;
	private static GameObject down;
	
	public static Score score;
	//chance of spawning a seeker
	static float chance;
	//should we spawn more enimies?
	static boolean moreEnemies;
	//spawn new seekers?
	public static boolean moreSeeker;
	//yay for timing
	private static int counter;
	//obvious name is obvious
	private static HealthBar hp;
	
	public static void init(){
		//how many seekers in the lot
		chance = 5;
		//load textures
		bullet = Texture.loadTexture("assets/bullet.png");
		shipFire = Texture.loadTexture("assets/ship_fire.png");
		shipNoFire = Texture.loadTexture("assets/Ship_noFire.png");
		asteriod = Texture.loadTexture("assets/Asteriod.png");	
		numEnemy = 0;
		p1 = new Player(400,300);
		bullets = new ArrayList<Bullet>();
		Enemies = new ArrayList<Enemy>();
		dead = new ArrayList<Enemy>();
		
		left = new GameObject(-p1.dy-1,Main.HEIGHT/2,1,Main.HEIGHT + 100,0);
		right = new GameObject(Main.WIDTH + p1.dy + 1,Main.HEIGHT/2,1,Main.HEIGHT + 100,0);
		up = new GameObject(Main.WIDTH/2,Main.HEIGHT + p1.dy + 1,Main.WIDTH + 100,1,0);
		down = new GameObject(Main.WIDTH/2,0 - p1.dy,Main.WIDTH + 100,1,0);
		moreEnemies = false;
		moreSeeker = false;
		counter = 0;
		score = new Score(770, 570);
		
		Main.soundSystem.backgroundMusic("background", "Background.wav", true);
		
		
		for(int i = 0; i < 4; i++){
			new Enemy(randomf(50,Main.WIDTH - 50),randomf(50,Main.HEIGHT - 50),randomf(0,360));
			numEnemy++;
		}
	
		hp = new HealthBar(10,580,400,p1);
	}
	
	public static float randomf(float min, float max){
		Random r = new Random();
		return r.nextFloat() * (max - min) + min;
		
	}
	
	
	public static void update(){
		counter ++;
		//every 20 seconds
		if(counter > 20 * 60){
			counter = 0;
			moreEnemies = true;
		}
		chance++;
		//20 seconds?
		if(chance >= 10*60){
				moreSeeker = true;
			chance = 0;
		}
		
		if(moreSeeker){
			new Seeker(randomf(50, Main.WIDTH - 50),randomf(50, Main.WIDTH - 50),p1);
			moreSeeker = false;
		}
		
		if(moreEnemies){
			//if there are less than 20 enemies, and there are more enemies in the array
			if(numEnemy<= 20 && Enemies.size() >= numEnemy){
				new Enemy(randomf(50,Main.WIDTH - 50),randomf(50,Main.HEIGHT - 50),randomf(0,360));
				numEnemy++;
			}
			moreEnemies = false;
		}
		
		p1.update();
		for(Bullet b : bullets){
			b.update();
		}
		
		for(Enemy e : dead){
			Enemies.remove(e);
			if(!e.seeker){
				numEnemy --;
			}
		}
		dead.clear();
		for(Enemy e : Enemies){
			e.update();
			for(Bullet b : bullets){
				if(e.collides(b) && e.alive && b.alive){
					e.kill();
					b.kill();
					moreEnemies = true;
					score.incScore(1);
					System.out.println("score: " + score.getScore());
				}
			}
			if(e.collides(p1) && e.alive){
				e.kill();
				p1.damage(10);
				score.incScore(1);
				moreEnemies = true;
			}
			
			if(e.collides(up) || e.collides(down) || e.collides(left) || e.collides(right)){
				moreEnemies = true;
				//tp them far away so that you can not hearsound
				e.x = -10000;
				e.y = -10000;
				e.kill();
			}
		}
		
		
		if(p1.collides(left)){
			p1.x = Main.WIDTH + p1.dy/2;
		}else if(p1.collides(right)){
			p1.x = 0 - p1.dy/2;
		}
		if(p1.collides(up)){
			p1.y = 0 - p1.dy/2;
		}else if(p1.collides(down)){
			p1.y = Main.HEIGHT + p1.dy/2;
		}
		if(p1.health <= 0){
			//TODO play big splosion graphic on screen
			Main.soundSystem.play("BigExplosion");
			try {
				Thread.sleep(10);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("You died. Ending score was:" + score.getScore());
			Main.close();
		}
		hp.update();
		
	}
	
	public static void render(){
		p1.draw();
		hp.draw();
		score.draw();
		
		for(Bullet b : bullets){
			b.draw();
		}
		
		for(Enemy e : Enemies){
			e.draw();
		}
		
		left.draw();
		right.draw();
		up.draw();
		down.draw();
	}
	
}
