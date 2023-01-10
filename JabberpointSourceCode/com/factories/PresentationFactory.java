package com.factories;

import com.presentations.DemoPresentation;
import com.presentations.Presentation;

public class PresentationFactory
{
    public Presentation createPresentation(String type)
    {
        return switch (type)
        {
            case "normal" -> new Presentation();
            case "demo" -> new DemoPresentation();
            default -> null;
        };
    }
}
