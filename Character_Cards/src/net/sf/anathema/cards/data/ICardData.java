package net.sf.anathema.cards.data;

import java.awt.Image;

import com.itextpdf.text.Phrase;

public interface ICardData {
	String getTitle();
	
	Image getPrimaryIcon();
	
	Image getSecondaryIcon();
	
	Phrase[] getStats();
	
	String getKeywords();
	
	Phrase[] getBody();
	
	String getSource();	
}
