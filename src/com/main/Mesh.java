package com.main;

import static org.lwjgl.opengl.GL11.*;

public class Mesh {

	public static void draw(float x, float y, float dx, float dy, float rz){
		
		glDisable(GL_TEXTURE_2D);
		glPushMatrix();
		//TODO Bug here.
		//for some reason yand x are flipped?
		glTranslatef(x,y,0);
		glRotatef(rz,0,0,1);
		
		glBegin(GL_QUADS);
		glVertex2f(-dx/2,-dy/2);
		glVertex2f(-dx/2,dy/2);
		glVertex2f(dx/2,dy/2);
		glVertex2f(dx/2,-dy/2);
		glEnd();
		glPopMatrix();
		glEnable(GL_TEXTURE_2D);
	}
	
public static void draw(float x, float y, float dx, float dy, float rz, Texture tex){
		//TODO test to see if textures work
		glPushMatrix();
		glTranslatef(x,y,0);
		glRotatef(rz,0,0,1);
		glRotatef(180,0,1,0);
		glBindTexture(GL_TEXTURE_2D, tex.id);
		//TODO Bug here.
		//for some reason yand x are flipped?
		
		
		
		glBegin(GL_QUADS);
		glTexCoord2f(0,0);
		glVertex2f(-dx/2,-dy/2);
		glTexCoord2f(0, 1);
		glVertex2f(-dx/2,dy/2);
		glTexCoord2f(1, 1);
		glVertex2f(dx/2,dy/2);
		glTexCoord2f(1, 0);
		glVertex2f(dx/2,-dy/2);
		glEnd();
		
		glPopMatrix();
		
	}

}
