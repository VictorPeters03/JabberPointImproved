import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * <p>An Accessor makes it possible to read and write data
 * for a presentation.</p>
 * <p>Non-abstract subclasses should implement the load and save methods.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public abstract class Accessor {

	public Accessor() {
	}

	abstract public void loadFile(Presentation p, String fn) throws IOException;

	public void saveFile(Presentation presentation, String filename) throws IOException
	{
		PrintWriter out = new PrintWriter(new FileWriter(filename));
		out.println("<?xml version=\"1.0\"?>");
		out.println("<!DOCTYPE presentation SYSTEM \"jabberpoint.dtd\">");
		out.println("<presentation>");
		out.print("<showtitle>" + presentation.getTitle() + "</showtitle>");
		//        for (int slideNumber=0; slideNumber<presentation.getSize(); slideNumber++) {
//            Slide slide = presentation.getSlide(slideNumber);
//            out.println("<slide>");
//            out.println("<title>" + slide.getTitle() + "</title>");
//            Vector<SlideItem> slideItems = slide.getSlideItems();
//            for (int itemNumber = 0; itemNumber<slideItems.size(); itemNumber++) {
//                SlideItem slideItem = (SlideItem) slideItems.elementAt(itemNumber);
//                out.print("<item kind=");
//                if (slideItem instanceof TextItem) {
//                    out.print("\"text\" level=\"" + slideItem.getLevel() + "\">");
//                    out.print( ( (TextItem) slideItem).getText());
//                }
//                else {
//                    if (slideItem instanceof BitmapItem) {
//                        out.print("\"image\" level=\"" + slideItem.getLevel() + "\">");
//                        out.print( ( (BitmapItem) slideItem).getName());
//                    }
//                    else {
//                        System.out.println("Ignoring " + slideItem);
//                    }
//                }
//                out.println("</item>");
//            }
//            out.println("</slide>");
//        }
		for (Slide slide : presentation.getShowList())
		{
			out.println("<slide>");
			out.println("<title>" + slide.getTitle() + "</title>");
			slide.saveSlideItemsToSlide(out);
			out.println("</slide>");
		}
		out.println("</presentation>");
		out.close();
	}

}
