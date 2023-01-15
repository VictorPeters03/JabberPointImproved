package com.factories;

import com.presentations.DemoPresentation;
import com.presentations.Presentation;
import com.enums.PresentationType;

public class PresentationFactory
{
    public static Presentation createPresentation(PresentationType type)
    {
        return switch (type)
        {
            case NORMAL -> new Presentation();
            case DEMO -> new DemoPresentation();
        };
    }
}
