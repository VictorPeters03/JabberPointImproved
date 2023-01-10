package com.main;

import com.accessors.XMLAccessor;
import com.factories.PresentationFactory;
import com.factories.StyleFactory;
import com.presentations.Presentation;
import com.slideitems.Style;
import com.slideviewer.SlideViewerFrame;

import javax.swing.*;
import java.io.IOException;

/** com.main.JabberPoint Main Program
 * <p>This program is distributed under the terms of the accompanying
 * COPYRIGHT.txt file (which is NOT the GNU General Public License).
 * Please read it. Your use of the software constitutes acceptance
 * of the terms in the COPYRIGHT.txt file.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class JabberPoint {
	protected static final String IOERR = "IO Error: ";
	protected static final String JABERR = "Jabberpoint Error ";
	protected static final String JABVERSION = "Jabberpoint 1.6 - OU version";

	private static Style[] styles; // de styles

	public static void createStyles()
	{
		styles = new Style[5];
		StyleFactory styleFactory = new StyleFactory();
		// De styles zijn vast ingecodeerd.
		styles[0] = styleFactory.createStyle(0);    // style voor item-level 0
		styles[1] = styleFactory.createStyle(1);    // style voor item-level 1
		styles[2] = styleFactory.createStyle(2);    // style voor item-level 2
		styles[3] = styleFactory.createStyle(3);    // style voor item-level 3
		styles[4] = styleFactory.createStyle(4);    // style voor item-level 4
	}

	public static Style getStyle(int level)
	{
		if (level >= styles.length)
		{
			level = styles.length - 1;
		}
		return styles[level];
	}

	/** The main program */
	public static void main(String[] argv) {
		
		createStyles();
		Presentation presentation;
		PresentationFactory presentationFactory = new PresentationFactory();
		try {
			if (argv.length == 0) { //a demo presentation
				presentation = presentationFactory.createPresentation("demo");
				new SlideViewerFrame(JABVERSION, presentation);
			} else {
				presentation = presentationFactory.createPresentation("normal");
				new SlideViewerFrame(JABVERSION, presentation);
				new XMLAccessor().loadFile(presentation, argv[0]);
			}
			presentation.setSlideNumber(0);
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null,
					IOERR + ex, JABERR,
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
