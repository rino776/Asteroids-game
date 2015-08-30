package com.main;

import static org.lwjgl.opengl.GL11.*;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;

public class Texture {
	// ID of this texture
	public int id;

	// Width of this texture
	public int width;

	// height of this texture
	public int height;

	// private constructor to prevent duplication
	private Texture(int id, int width, int height) {
		this.id = id;
		this.width = width;
		this.height = height;
	}

	/**
	 * Load the texture
	 */
	public static Texture loadTexture(String name) {
		// load and return the texture
		BufferedImage bimg = null;

		try {
			bimg = ImageIO.read(Texture.class.getClassLoader()
					.getResourceAsStream(name));
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Unable to load textures");

		}

		int[] pixels = new int[bimg.getWidth() * bimg.getHeight() * 4];
		bimg.getRGB(0, 0, bimg.getWidth(), bimg.getHeight(), pixels, 0,
				bimg.getWidth());

		ByteBuffer buffer = BufferUtils.createByteBuffer(pixels.length);
		for (int y = 0; y < bimg.getHeight(); y++) {
			for (int x = 0; x < bimg.getWidth(); x++) {
				// select pixel
				int pixel = pixels[y * bimg.getWidth() + x];
				// Add Red componant
				buffer.put((byte) ((pixel >> 16) & 0xFF));
				// Add Green componant
				buffer.put((byte) ((pixel >> 8) & 0xFF));
				// Add Blue componant
				buffer.put((byte) (pixel & 0xFF));
				// Add Alpha componant
				buffer.put((byte) ((pixel >> 24) & 0xFF));
			}
		}
		// flip the buffer to reset position pointer
		buffer.flip();

		// generate a texture ID
		int textureID = glGenTextures();
		// bind the texture handle to hte context
		glBindTexture(GL_TEXTURE_2D, textureID);

		// setup texture scaling filtering
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

		// set Texture data to openGL
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, bimg.getWidth(),
				bimg.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);

		return new Texture(textureID, bimg.getWidth(), bimg.getHeight());
	}

}
