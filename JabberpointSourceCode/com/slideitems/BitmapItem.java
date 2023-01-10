package com.slideitems;

import com.main.JabberPoint;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;


/** <p>The class for a Bitmap item</p>
 * <p>Bitmap items are responsible for drawing themselves.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
*/

public class BitmapItem extends SlideItem
{
  private BufferedImage bufferedImage;
  private String imageName;
  
  protected static final String FILE = "File ";
  protected static final String NOTFOUND = " not found";


  	//level indicates the item-level; name indicates the name of the file with the image
	public BitmapItem(int level, String name) {
		super(level);
		imageName = name;
		try {
			bufferedImage = ImageIO.read(new File(imageName));
		}
		catch (IOException e) {
			System.err.println(FILE + imageName + NOTFOUND) ;
		}
	}

	//Returns the filename of the image
	public String getName() {
		return imageName;
	}

	//Returns the bounding box of the image
	public Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale) {
		return new Rectangle((int) (JabberPoint.getStyle(level).indent * scale), 0,
				(int) (bufferedImage.getWidth(observer) * scale),
				((int) (JabberPoint.getStyle(level).leading * scale)) +
				(int) (bufferedImage.getHeight(observer) * scale));
	}

	//Draws the image
	public void draw(int x, int y, float scale, Graphics g, ImageObserver observer) {
		int width = x + (int) (JabberPoint.getStyle(level).indent * scale);
		int height = y + (int) (JabberPoint.getStyle(level).leading * scale);
		g.drawImage(bufferedImage, width, height,(int) (bufferedImage.getWidth(observer)*scale),
                (int) (bufferedImage.getHeight(observer)*scale), observer);
	}

	public String toString() {
		return "com.slideitems.BitmapItem[" + getLevel() + "," + imageName + "]";
	}

	public void printItem(PrintWriter out)
	{
		out.print("\"image\" level=\"" + getLevel() + "\">");
		out.print(getName());
	}
}
