package com.menubuilders;

import com.presentations.Presentation;

import java.awt.*;

public abstract class MenuBuilder
{
    public MenuItem mkMenuItem(String name)
    {
        return new MenuItem(name, new MenuShortcut(name.charAt(0)));
    }

    public abstract void buildMenu(Frame parent, Presentation pres, Menu menu);
}
