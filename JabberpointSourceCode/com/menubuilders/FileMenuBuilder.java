package com.menubuilders;

import com.accessors.Accessor;
import com.accessors.XMLAccessor;
import com.controllers.JabberPointMenuItems;
import com.presentations.Presentation;

import javax.swing.*;
import java.io.IOException;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.MenuShortcut;

public class FileMenuBuilder implements IMakeMenuItem
{
    protected static final String SAVEFILE = "savedPresentation.xml";
    protected static final String IOEX = "IO Exception: ";
    protected static final String LOADERR = "Load Error";
    protected static final String SAVEERR = "Save Error";

    public void buildMenu(Frame parent, Presentation pres, Menu fileMenu)
    {
        MenuItem menuItem;
        fileMenu.add(menuItem = mkMenuItem(JabberPointMenuItems.OPEN));
        addOpenPresentationAction(menuItem, pres, parent);
        fileMenu.add(menuItem = mkMenuItem(JabberPointMenuItems.NEW));
        addNewPresentationAction(menuItem, pres, parent);
        fileMenu.add(menuItem = mkMenuItem(JabberPointMenuItems.SAVE));
        addSavePresentationAction(menuItem, pres, parent);
        fileMenu.addSeparator();
        fileMenu.add(menuItem = mkMenuItem(JabberPointMenuItems.EXIT));
        menuItem.addActionListener(actionEvent -> pres.exit(0));
    }

    public void addOpenPresentationAction(MenuItem menuItem, Presentation presentation, Frame parent)
    {
        menuItem.addActionListener(actionEvent ->
        {
            presentation.clear();
            Accessor xmlAccessor = new XMLAccessor();
            try
            {
                String presTitle = JOptionPane.showInputDialog("Enter name of presentation");
                xmlAccessor.loadFile(presentation, presTitle);
                presentation.setSlideNumber(0);
            } catch (IOException exc)
            {
                JOptionPane.showMessageDialog(parent, IOEX + exc,
                        LOADERR, JOptionPane.ERROR_MESSAGE);
            }
            parent.repaint();
        });
    }

    public void addNewPresentationAction(MenuItem menuItem, Presentation presentation, Frame parent)
    {
        menuItem.addActionListener(actionEvent ->
        {
            presentation.clear();
            parent.repaint();
        });
    }

    public void addSavePresentationAction(MenuItem menuItem, Presentation presentation, Frame parent)
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

    @Override
    public MenuItem mkMenuItem(String name)
    {
        return new MenuItem(name, new MenuShortcut(name.charAt(0)));
    }

}
