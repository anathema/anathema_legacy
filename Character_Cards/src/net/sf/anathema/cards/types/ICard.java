package net.sf.anathema.cards.types;

import java.awt.Image;

import com.itextpdf.text.Phrase;

public interface ICard {
	String getTitle();
	
	Image getPrimaryIcon();
	
	Image getSecondaryIcon();
	
	Phrase[] getStats();
	
	String getKeywords();
	
	Phrase[] getBody();
	
	String getSource();	
}
