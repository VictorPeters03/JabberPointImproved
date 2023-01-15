package com.main;

import com.enums.AccessorType;
import com.factories.AccessorFactory;
import com.factories.PresentationFactory;
import com.presentations.Presentation;
import com.enums.PresentationType;
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

	// de styles

	/** The main program */
	public static void main(String[] argv) {
		Presentation presentation;
		try {
			if (argv.length == 0) { //a demo presentation
				presentation = PresentationFactory.createPresentation(PresentationType.DEMO);
				new SlideViewerFrame(JABVERSION, presentation);
			} else {
				presentation = PresentationFactory.createPresentation(PresentationType.NORMAL);
				new SlideViewerFrame(JABVERSION, presentation);
				AccessorFactory.buildAccessor(AccessorType.XML).loadFile(presentation, argv[0]);
			}
			presentation.setSlideNumber(0);
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null,
					IOERR + ex, JABERR,
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
