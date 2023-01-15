package com.accessors;

import com.enums.SlideItemType;
import com.factories.SlideItemFactory;
import com.presentations.Presentation;
import com.slide.Slide;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * com.accessors.XMLAccessor, reads and writes XML files
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class XMLAccessor extends Accessor
{
    /**
     * Text of messages
     */
    protected static final String PCE = "Parser Configuration Exception";
    protected static final String UNKNOWNTYPE = "Unknown Element type";
    protected static final String NFE = "Number Format Exception";


    private String getTitle(Element element, String tagName)
    {
        NodeList titles = element.getElementsByTagName(tagName);
        return titles.item(0).getTextContent();

    }

    public void loadFile(Presentation presentation, String filename)
    {
        int slideNumber, itemNumber, max, maxItems;
        try
        {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.parse(new File(filename)); //Create a JDOM document
            Element doc = document.getDocumentElement();
            presentation.setTitle(getTitle(doc, XMLTagNames.SHOWTITLE));

            NodeList slides = doc.getElementsByTagName(XMLTagNames.SLIDE);
            max = slides.getLength();
            for (slideNumber = 0; slideNumber < max; slideNumber++)
            {
                Element xmlSlide = (Element) slides.item(slideNumber);
                Slide slide = new Slide();
                slide.setTitle(getTitle(xmlSlide, XMLTagNames.SLIDETITLE));
                presentation.append(slide);

                NodeList slideItems = xmlSlide.getElementsByTagName(XMLTagNames.ITEM);
                maxItems = slideItems.getLength();
                for (itemNumber = 0; itemNumber < maxItems; itemNumber++)
                {
                    Element item = (Element) slideItems.item(itemNumber);
                    loadSlideItem(slide, item);
                }
            }
        } catch (IOException iox)
        {
            System.err.println(iox);
        } catch (SAXException sax)
        {
            System.err.println(sax.getMessage());
        } catch (ParserConfigurationException pcx)
        {
            System.err.println(PCE);
        }
    }

    protected void loadSlideItem(Slide slide, Element item)
    {
        int level = 1; // default
        NamedNodeMap attributes = item.getAttributes();
        String leveltext = attributes.getNamedItem(XMLTagNames.LEVEL).getTextContent();
        if (leveltext != null)
        {
            try
            {
                level = Integer.parseInt(leveltext);
            } catch (NumberFormatException x)
            {
                System.err.println(NFE);
            }
        }
        String type = attributes.getNamedItem(XMLTagNames.KIND).getTextContent();
        if (XMLTagNames.TEXT.equals(type))
        {
            slide.append(SlideItemFactory.buildSlideItem(SlideItemType.TEXT, level, item.getTextContent()));
        }
        else
        {
            if (XMLTagNames.IMAGE.equals(type))
            {
                slide.append(SlideItemFactory.buildSlideItem(SlideItemType.BITMAP, level, item.getTextContent()));
            }
            else
            {
                System.err.println(UNKNOWNTYPE);
            }
        }
    }

    public void saveFile(Presentation presentation, String filename) throws IOException
    {
        PrintWriter out = new PrintWriter(new FileWriter(filename));
        out.println("<?xml version=\"1.0\"?>");
        out.println("<!DOCTYPE presentation SYSTEM \"jabberpoint.dtd\">");
        out.println("<presentation>");
        out.print("<showtitle>" + presentation.getTitle() + "</showtitle>");
        for (Slide slide : presentation.getSlides())
        {
            out.println("<slide>");
            out.println("<title>" + slide.getTitle() + "</title>");
            slide.saveSlideItems(out);
            out.println("</slide>");
        }
        out.println("</presentation>");
        out.close();
    }
}
