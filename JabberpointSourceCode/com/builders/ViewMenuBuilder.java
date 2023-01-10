package com.builders;

import javax.swing.*;
import java.awt.*;

public class ViewMenuBuilder implements IMakeMenuItem
{
    public void buildMenu(Presentation pres, Menu viewMenu)
    {
        MenuItem menuItem;
        viewMenu.add(menuItem = mkMenuItem(JabberPointMenuItems.NEXT));
        addNextSlideAction(menuItem, pres);
        viewMenu.add(menuItem = mkMenuItem(JabberPointMenuItems.PREV));
        addPreviousSlideAction(menuItem, pres);
        viewMenu.add(menuItem = mkMenuItem(JabberPointMenuItems.GOTO));
        addGotoSlideAction(menuItem, pres);
    }

    public void addNextSlideAction(MenuItem menuItem, Presentation presentation)
    {
        menuItem.addActionListener(actionEvent -> presentation.nextSlide());
    }

    public void addPreviousSlideAction(MenuItem menuItem, Presentation presentation)
    {
        menuItem.addActionListener(actionEvent -> presentation.prevSlide());
    }

    public void addGotoSlideAction(MenuItem menuItem, Presentation presentation)
    {
        menuItem.addActionListener(actionEvent ->
        {
            String pageNumberStr = JOptionPane.showInputDialog(JabberPointMenuItems.PAGENR);
            int pageNumber = Integer.parseInt(pageNumberStr);
            presentation.setSlideNumber(pageNumber - 1);
        });
    }

    @Override
    public MenuItem mkMenuItem(String name)
    {
        return new MenuItem(name, new MenuShortcut(name.charAt(0)));
    }
}
