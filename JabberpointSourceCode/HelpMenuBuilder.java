import java.awt.*;

public class HelpMenuBuilder extends MenuBar implements IMakeMenuItem
{
    public HelpMenuBuilder(Frame parent, Presentation pres, Menu helpMenu)
    {
        MenuItem menuItem;
        helpMenu.add(menuItem = mkMenuItem(JabberPointMenuItems.ABOUT));
        addShowAboutBoxAction(menuItem, parent);
    }

    public void addShowAboutBoxAction(MenuItem menuItem, Frame parent)
    {
        menuItem.addActionListener(actionEvent -> AboutBox.show(parent));
    }

    @Override
    public MenuItem mkMenuItem(String name)
    {
        return new MenuItem(name, new MenuShortcut(name.charAt(0)));
    }
}
