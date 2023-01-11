package com.factories;

import com.accessors.Accessor;
import com.accessors.AccessorType;
import com.accessors.XMLAccessor;
import com.presentations.DemoPresentation;
import com.presentations.Presentation;

public class AccessorFactory
{
    public static Accessor buildAccessor(AccessorType type)
    {
        return switch (type)
        {
            case XML -> new XMLAccessor();
        };
    }
}
