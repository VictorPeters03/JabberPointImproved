package com.presentations;

import com.slide.Slide;
import com.slideviewer.SlideViewerComponent;

import java.util.ArrayList;


/**
 * <p>Presentations keeps track of the slides in a presentation.</p>
 * <p>Only one instance of this class is available.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class Presentation {
	protected String showTitle; //The title of the presentation
	private ArrayList<Slide> slides; //An ArrayList with slides
	private int currentSlideNumber = 0; //The number of the current slide
	private SlideViewerComponent slideViewComponent; //The view component of the slides

	public Presentation() {
		slideViewComponent = null;
		this.slides = new ArrayList<>();
	}

	public int getSize() {
		return slides.size();
	}

	public ArrayList<Slide> getSlides()
	{
		return slides;
	}

	public String getTitle() {
		return showTitle;
	}

	public void setTitle(String nt) {
		showTitle = nt;
	}

	public void setShowView(SlideViewerComponent slideViewerComponent) {
		this.slideViewComponent = slideViewerComponent;
	}

	//Returns the number of the current slide
	public int getSlideNumber() {
		return currentSlideNumber;
	}

	//Change the current slide number and report it the window
	public void setSlideNumber(int number) {
		currentSlideNumber = number;
		if (slideViewComponent != null && number < slides.size() && number >= 0) {
			slideViewComponent.update(this, getCurrentSlide());
		}
	}

	//Navigate to the previous slide unless we are at the first slide
	public void prevSlide() {
		if (currentSlideNumber > 0) {
			setSlideNumber(currentSlideNumber - 1);
	    }
	}

	//Navigate to the next slide unless we are at the last slide
	public void nextSlide() {
		if (currentSlideNumber < (slides.size()-1)) {
			setSlideNumber(currentSlideNumber + 1);
		}
	}

	//Remove the presentation
	public void clear() {
		slides = new ArrayList<>();
		setSlideNumber(-1);
	}

	//Add a slide to the presentation
	public void append(Slide slide) {
		slides.add(slide);
	}

	//Return a slide with a specific number
	public Slide getSlide(int number) {
		return number < 0 || number >= getSize() ? null : slides.get(number);
	}

	//Return the current slide
	public Slide getCurrentSlide() {
		return getSlide(currentSlideNumber);
	}

	public void exit(int n) {
		System.exit(n);
	}
}
