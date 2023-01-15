package com.factories;

import com.enums.SlideItemType;
import com.slideitems.BitmapItem;
import com.slideitems.SlideItem;
import com.slideitems.TextItem;

public class SlideItemFactory
{
    public static SlideItem buildSlideItem(SlideItemType type, int level, String name)
    {
        return switch (type)
                {
                    case BITMAP -> new BitmapItem(level, name);
                    case TEXT -> new TextItem(level, name);
                };
    }
}
