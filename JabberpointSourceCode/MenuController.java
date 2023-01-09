import java.awt.*;

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

    public MenuController(Frame frame, Presentation pres)
    {
        parent = frame;
        presentation = pres;
        Menu fileMenuContainer = new Menu(JabberPointMenuItems.FILE);
        new FileMenuBuilder(this.parent, pres, fileMenuContainer);
        add(fileMenuContainer);
        Menu viewMenuContainer = new Menu(JabberPointMenuItems.VIEW);
        new ViewMenuBuilder(pres, viewMenuContainer);
        add(viewMenuContainer);
        Menu helpMenuContainer = new Menu(JabberPointMenuItems.HELP);
        new HelpMenuBuilder(this.parent, pres, helpMenuContainer);
        setHelpMenu(helpMenuContainer);        //Needed for portability (Motif, etc.).
    }

    public Presentation getPresentation()
    {
        return presentation;
    }
}
