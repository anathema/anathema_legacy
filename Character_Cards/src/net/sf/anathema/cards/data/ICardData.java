package net.sf.anathema.cards.data;

import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;

public interface ICardData {
	String getTitle();
	
	Image getPrimaryIcon();
	
	Image getSecondaryIcon();
	
	Phrase[] getStats();
	
	String getKeywords();
	
	Element[] getBody(int contentHeight);
	
	String getSource();	
}
