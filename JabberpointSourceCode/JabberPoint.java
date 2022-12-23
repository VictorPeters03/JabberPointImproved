import javax.swing.JOptionPane;

import java.awt.*;
import java.io.IOException;

/** JabberPoint Main Program
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
		// De styles zijn vast ingecodeerd.
		styles[0] = new Style(0, Color.red, 48, 20);    // style voor item-level 0
		styles[1] = new Style(20, Color.blue, 40, 10);    // style voor item-level 1
		styles[2] = new Style(50, Color.black, 36, 10);    // style voor item-level 2
		styles[3] = new Style(70, Color.black, 30, 10);    // style voor item-level 3
		styles[4] = new Style(90, Color.black, 24, 10);    // style voor item-level 4
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
		Presentation presentation = new Presentation();
		new SlideViewerFrame(JABVERSION, presentation);
		try {
			if (argv.length == 0) { //a demo presentation
				Accessor.getDemoAccessor().loadFile(presentation, "");
			} else {
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
