package com.slideviewer;

import com.presentations.Presentation;
import com.slide.Slide;

import javax.swing.*;
import java.awt.*;


/** <p>com.slideviewer.SlideViewerComponent is a graphical component that ca display Slides.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class SlideViewerComponent extends JComponent {
		
	private Slide slide; //The current slide
	private Font labelFont; //The font for labels
	private Presentation presentation; //The presentation
	private JFrame frame;

	public SlideViewerComponent(Presentation pres, JFrame frame) {
		setBackground(SlideProperties.BGCOLOR);
		presentation = pres;
		labelFont = new Font(SlideProperties.FONTNAME, SlideProperties.FONTSTYLE, SlideProperties.FONTHEIGHT);
		this.frame = frame;
	}

	public Dimension getPreferredSize() {
		return new Dimension(Slide.WIDTH, Slide.HEIGHT);
	}

	public void update(Presentation presentation, Slide data) {
		if (data == null) {
			repaint();
			return;
		}
		this.presentation = presentation;
		this.slide = data;
		repaint();
		frame.setTitle(presentation.getTitle());
	}

//Draw the slide
	public void paintComponent(Graphics g) {
		g.setColor(SlideProperties.BGCOLOR);
		g.fillRect(0, 0, getSize().width, getSize().height);
		if (presentation.getSlideNumber() < 0 || slide == null) {
			return;
		}
		g.setFont(labelFont);
		g.setColor(SlideProperties.COLOR);
		g.drawString("Slide " + (1 + presentation.getSlideNumber()) + " of " +
                 presentation.getSize(), SlideProperties.XPOS, SlideProperties.YPOS);
		Rectangle area = new Rectangle(0, SlideProperties.YPOS, getWidth(), (getHeight() - SlideProperties.YPOS));
		slide.draw(g, area, this);
	}
}
