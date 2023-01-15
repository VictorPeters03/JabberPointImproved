package com.menubuilders;

import com.controllers.JabberPointMenuItems;
import com.about.AboutBox;
import com.presentations.Presentation;

import java.awt.*;

public class HelpMenuBuilder extends MenuBuilder
{
    public void buildMenu(Frame parent, Presentation pres, Menu helpMenu)
    {
        MenuItem menuItem;
        helpMenu.add(menuItem = mkMenuItem(JabberPointMenuItems.ABOUT));
        addShowAboutBoxAction(menuItem, parent);
    }

    public void addShowAboutBoxAction(MenuItem menuItem, Frame parent)
    {
        menuItem.addActionListener(actionEvent -> AboutBox.show(parent));
    }
}
