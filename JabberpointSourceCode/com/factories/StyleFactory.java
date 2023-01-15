package com.factories;

import com.slideitems.Style;

import java.awt.*;

public class StyleFactory
{
    public static Style buildStyle(int item)
    {
        return switch (item)
        {
            case 0 -> new Style(0, Color.red, 48, 20);
            case 1 -> new Style(20, Color.blue, 40, 10);
            case 2 -> new Style(50, Color.black, 36, 10);
            case 3 -> new Style(70, Color.black, 30, 10);
            case 4 -> new Style(90, Color.black, 24, 10);
            default -> throw new IllegalStateException("Unexpected value: " + item);
        };
    }
}
