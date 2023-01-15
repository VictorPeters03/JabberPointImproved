package com.factories;

import com.enums.MenuBuilderType;
import com.menubuilders.*;

public class MenuBuilderFactory
{
    public static MenuBuilder buildMenuBuilder(MenuBuilderType type)
    {
        return switch (type)
        {
            case FILE -> new FileMenuBuilder();
            case HELP -> new HelpMenuBuilder();
            case VIEW -> new ViewMenuBuilder();
        };
    }
}
