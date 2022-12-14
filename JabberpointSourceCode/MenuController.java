import java.awt.MenuBar;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.io.IOException;

import javax.swing.JOptionPane;

/**
 * <p>The controller for the menu</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public class MenuController extends MenuBar
{

    private Frame parent; //The frame, only used as parent for the Dialogs
    private Presentation presentation; //Commands are given to the presentation

    private static final long serialVersionUID = 227L;

    protected static final String TESTFILE = "testPresentation.xml";
    protected static final String SAVEFILE = "savedPresentation.xml";

    protected static final String IOEX = "IO Exception: ";
    protected static final String LOADERR = "Load Error";
    protected static final String SAVEERR = "Save Error";

    public MenuController(Frame frame, Presentation pres)
    {
        parent = frame;
        presentation = pres;
        MenuItem menuItem;
        Menu fileMenu = new Menu(JabberPointMenuItems.FILE);
        fileMenu.add(menuItem = mkMenuItem(JabberPointMenuItems.OPEN));
        menuItem.addActionListener(actionEvent ->
        {
            presentation.clear();
            Accessor xmlAccessor = new XMLAccessor();
            try
            {
                xmlAccessor.loadFile(presentation, TESTFILE);
                presentation.setSlideNumber(0);
            } catch (IOException exc)
            {
                JOptionPane.showMessageDialog(parent, IOEX + exc,
                        LOADERR, JOptionPane.ERROR_MESSAGE);
            }
            parent.repaint();
        });
        fileMenu.add(menuItem = mkMenuItem(JabberPointMenuItems.NEW));
        menuItem.addActionListener(actionEvent ->
        {
            presentation.clear();
            parent.repaint();
        });
        fileMenu.add(menuItem = mkMenuItem(JabberPointMenuItems.SAVE));
        menuItem.addActionListener(e ->
        {
            Accessor xmlAccessor = new XMLAccessor();
            try
            {
                xmlAccessor.saveFile(presentation, SAVEFILE);
            } catch (IOException exc)
            {
                JOptionPane.showMessageDialog(parent, IOEX + exc,
                        SAVEERR, JOptionPane.ERROR_MESSAGE);
            }
        });
        fileMenu.addSeparator();
        fileMenu.add(menuItem = mkMenuItem(JabberPointMenuItems.EXIT));
        menuItem.addActionListener(actionEvent -> presentation.exit(0));
        add(fileMenu);
        Menu viewMenu = new Menu(JabberPointMenuItems.VIEW);
        viewMenu.add(menuItem = mkMenuItem(JabberPointMenuItems.NEXT));
        menuItem.addActionListener(actionEvent -> presentation.nextSlide());
        viewMenu.add(menuItem = mkMenuItem(JabberPointMenuItems.PREV));
        menuItem.addActionListener(actionEvent -> presentation.prevSlide());
        viewMenu.add(menuItem = mkMenuItem(JabberPointMenuItems.GOTO));
        menuItem.addActionListener(actionEvent ->
        {
            String pageNumberStr = JOptionPane.showInputDialog(JabberPointMenuItems.PAGENR);
            int pageNumber = Integer.parseInt(pageNumberStr);
            presentation.setSlideNumber(pageNumber - 1);
        });
        add(viewMenu);
        Menu helpMenu = new Menu(JabberPointMenuItems.HELP);
        helpMenu.add(menuItem = mkMenuItem(JabberPointMenuItems.ABOUT));
        menuItem.addActionListener(actionEvent -> AboutBox.show(parent));
        setHelpMenu(helpMenu);        //Needed for portability (Motif, etc.).
    }

    //Creating a menu-item
    public MenuItem mkMenuItem(String name)
    {
        return new MenuItem(name, new MenuShortcut(name.charAt(0)));
    }
}
