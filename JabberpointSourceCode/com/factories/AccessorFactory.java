package com.factories;

import com.accessors.Accessor;
import com.enums.AccessorType;
import com.accessors.XMLAccessor;

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
