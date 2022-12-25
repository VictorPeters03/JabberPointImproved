import java.awt.MenuBar;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.io.IOException;
import java.io.Serial;

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

    @Serial
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
        addOpenPresentationAction(menuItem);
        fileMenu.add(menuItem = mkMenuItem(JabberPointMenuItems.NEW));
        addNewPresentationAction(menuItem);
        fileMenu.add(menuItem = mkMenuItem(JabberPointMenuItems.SAVE));
        addSavePresentationAction(menuItem);
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
        addGotoSlideAction(menuItem);
        add(viewMenu);
        Menu helpMenu = new Menu(JabberPointMenuItems.HELP);
        helpMenu.add(menuItem = mkMenuItem(JabberPointMenuItems.ABOUT));
        menuItem.addActionListener(actionEvent -> AboutBox.show(parent));
        setHelpMenu(helpMenu);        //Needed for portability (Motif, etc.).
    }

    public void addOpenPresentationAction(MenuItem menuItem)
    {
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
    }

    public void addNewPresentationAction(MenuItem menuItem)
    {
        menuItem.addActionListener(actionEvent ->
        {
            presentation.clear();
            parent.repaint();
        });
    }

    public void addSavePresentationAction(MenuItem menuItem)
    {
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
    }

    public void addGotoSlideAction(MenuItem menuItem)
    {
        menuItem.addActionListener(actionEvent ->
        {
            String pageNumberStr = JOptionPane.showInputDialog(JabberPointMenuItems.PAGENR);
            int pageNumber = Integer.parseInt(pageNumberStr);
            presentation.setSlideNumber(pageNumber - 1);
        });
    }

    //Creating a menu-item
    public MenuItem mkMenuItem(String name)
    {
        return new MenuItem(name, new MenuShortcut(name.charAt(0)));
    }
}
