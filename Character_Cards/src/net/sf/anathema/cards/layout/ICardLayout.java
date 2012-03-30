package net.sf.anathema.cards.layout;

import com.itextpdf.text.DocumentException;

import net.sf.anathema.cards.ICard;

public interface ICardLayout {
	void drawCard(ICard card) throws DocumentException;
	
	ICardReportResourceProvider getResourceProvider();
	
	int getCardWidth();
	
	int getCardHeight();
}
