package com.main;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import paulscode.sound.SoundSystem;
import paulscode.sound.SoundSystemConfig;
import paulscode.sound.SoundSystemException;
import paulscode.sound.codecs.CodecWav;
import paulscode.sound.libraries.LibraryLWJGLOpenAL;
import static org.lwjgl.opengl.GL11.*;

public class Main {

	public final static float WIDTH = 800;
	public final static float HEIGHT = 600;
	private static final String TITLE = "shooty fun asteroid game";
	
	private boolean running = true;
	
	
	public static SoundSystem soundSystem;
	
	//TODO implement delta time
	
	public void start(){
		try {
			Display.setDisplayMode(new DisplayMode((int)WIDTH,(int)HEIGHT));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
		try {
			SoundSystemConfig.addLibrary(LibraryLWJGLOpenAL.class);
			SoundSystemConfig.setCodec("wav",CodecWav.class);
		} catch (SoundSystemException e) {
			e.printStackTrace();
		}
		
		//initialize sound system
		soundSystem = new SoundSystem();
		
		//set the listener to themiddle of the screen
		soundSystem.setListenerPosition(WIDTH/2, HEIGHT/2, 0);
		soundSystem.newSource(false, "laser", "laser.wav", false, 0, 0, 0, SoundSystemConfig.ATTENUATION_ROLLOFF, SoundSystemConfig.getDefaultRolloff());
		soundSystem.newSource(false, "explosion", "explosion.wav", false, 0, 0, 0, SoundSystemConfig.ATTENUATION_ROLLOFF, SoundSystemConfig.getDefaultRolloff());
		soundSystem.newSource(false, "explosion1", "explosion.wav", false, 0, 0, 0, SoundSystemConfig.ATTENUATION_ROLLOFF, SoundSystemConfig.getDefaultRolloff());
		soundSystem.newSource(false, "explosion2", "explosion.wav", false, 0, 0, 0, SoundSystemConfig.ATTENUATION_ROLLOFF, SoundSystemConfig.getDefaultRolloff());
		soundSystem.newSource(false, "BigExplosion", "BigExplosion.wav", false, 0, 0, 0, SoundSystemConfig.ATTENUATION_ROLLOFF, SoundSystemConfig.getDefaultRolloff());
		
		Display.setTitle(TITLE);
		
		initGl();
		Game.init();
		glEnable(GL_TEXTURE_2D);
		while(running){
			if(Display.isCloseRequested()){
				close();
			}
			update();
			render();
		}
		//wait for the final sound to finish playing
		soundSystem.stop("background");

		//wait until the sound stops
//		while(soundSystem.playing()){}
			
		Display.destroy();
		soundSystem.cleanup();
		
	}
	
	
	public void initGl(){
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0,800,0,600,1,-1);
		glEnable(GL_ALPHA);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA,GL_ONE_MINUS_SRC_ALPHA);
	}
	
	public void update(){
		Game.update();
		Display.update();
		Display.sync(60);
	}
	
	public void render(){
		glClear(GL_DEPTH_BUFFER_BIT | GL_COLOR_BUFFER_BIT);
		Game.render();
	}
	
	public static void close(){
		main.running = false;
	}
	
	private static Main main;
	
	public static void main(String[] args){
		main = new Main();
		main.start();
	}
	
}
