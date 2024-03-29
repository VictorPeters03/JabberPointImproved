package com.slide;

import com.enums.SlideItemType;
import com.factories.SlideItemFactory;
import com.slideitems.SlideItem;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.PrintWriter;
import java.util.Vector;

/**
 * <p>A slide. This class has drawing functionality.</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class Slide
{
    public final static int WIDTH = 1200;
    public final static int HEIGHT = 800;
    protected String title; //The title is kept separately
    protected Vector<SlideItem> items; //The SlideItems are kept in a vector

    public Slide()
    {
        items = new Vector<>();
    }

    //Add a SlideItem
    public void append(SlideItem anItem)
    {
        items.addElement(anItem);
    }

    //Return the title of a slide
    public String getTitle()
    {
        return title;
    }

    //Change the title of a slide
    public void setTitle(String newTitle)
    {
        title = newTitle;
    }

    //Create a TextItem out of a String and add the TextItem
    public void append(int level, String message)
    {
        append(SlideItemFactory.buildSlideItem(SlideItemType.TEXT, level, message));
    }

    //Draws the slide
    public void draw(Graphics g, Rectangle area, ImageObserver view)
    {
        float scale = getScale(area);
        int x = area.x;
        int y = area.y;
        //The title is treated separately
        SlideItem slideItemTitle = SlideItemFactory.buildSlideItem(SlideItemType.TEXT, 0, getTitle());
        slideItemTitle.draw(x, y, scale, g, view);
        y += slideItemTitle.getBoundingBox(g, view, scale).height;
        for (SlideItem slideItem : this.items)
        {
            slideItem.draw(x, y, scale, g, view);
            y += slideItem.getBoundingBox(g, view, scale).height;
        }
    }

    //Returns the scale to draw a slide
    private float getScale(Rectangle area)
    {
        return Math.min(((float) area.width) / ((float) WIDTH), ((float) area.height) / ((float) HEIGHT));
    }

    public void saveSlideItems(PrintWriter out)
    {
        for (SlideItem slideItem : this.items)
        {
            out.print("<item kind=");
            slideItem.printItem(out);
            out.println("</item>");
        }
    }
}
