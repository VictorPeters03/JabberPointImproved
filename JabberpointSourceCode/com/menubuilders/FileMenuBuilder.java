package com.menubuilders;

import com.accessors.Accessor;
import com.controllers.JabberPointMenuItems;
import com.enums.AccessorType;
import com.factories.AccessorFactory;
import com.presentations.Presentation;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class FileMenuBuilder extends MenuBuilder
{
    protected static final String SAVEFILE = "savedPresentation.xml";
    protected static final String IOEX = "IO Exception: ";
    protected static final String LOADERR = "Load Error";
    protected static final String SAVEERR = "Save Error";
    protected static final String TESTFILE = "testPresentation.xml";

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
            Accessor xmlAccessor = AccessorFactory.buildAccessor(AccessorType.XML);
            try
            {
                xmlAccessor.loadFile(presentation, TESTFILE);
                presentation.setSlideNumber(0);
            }
            catch (IOException exc)
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
            Accessor xmlAccessor = AccessorFactory.buildAccessor(AccessorType.XML);
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

}
