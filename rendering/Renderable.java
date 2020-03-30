package rendering;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class Renderable {
	
	// Superclass of all renderable objects in-game.
	// Also provides some handy static methods for rendering.
	
	// Does the object get hidden on escape or menu screens?
	boolean hideable;
	
	APanel panel;
	
	public Renderable(APanel panel, boolean hideable) {
		this.panel = panel;
		this.panel.objects.add(this);
		this.hideable = hideable;
	}
	
	// Must be overridden in subclasses.
	public abstract void render(Graphics g);
	
	//// STATIC METHODS BELOW ////
	
	// Rendering with a texture
	// xPos and yPos are the center, NOT the corner
	public static void render(Graphics g, int width, int height, int xPos, int yPos, String textureName) {
		Rectangle rect = new Rectangle(xPos - width/2, yPos - height/2, width, height);
		Graphics2D g2d = (Graphics2D) g.create();
		BufferedImage textureFile = null;
		// TODO TODO TODO PLEASE someone figure out how to change this file path to work everywhere
		String fileName = "/users/adamhutchings/Coding/asmura/textures/" + textureName + ".png";
		try {
			// TODO: Change file path for different users
			textureFile = ImageIO.read(new File(fileName));
		} catch (IOException ioexception) {
			System.out.println(ioexception.getMessage());
		}
		g2d.setPaint(new TexturePaint(textureFile, rect));
		g2d.fill(rect);
		g2d.dispose();
	}
	
	// Draw a string within a bounding box.
	public static void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
	    // Get the FontMetrics
	    FontMetrics metrics = g.getFontMetrics(font);
	    // Determine the X coordinate for the text
	    int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
	    // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
	    int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
	    // Set the font
	    g.setFont(font);
	    // Draw the String
	    g.drawString(text, x, y);
	}

}
