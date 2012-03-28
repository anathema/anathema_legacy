package net.sf.anathema.cards.layout;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfContentByte;

import net.sf.anathema.cards.types.ICard;

public interface ICardLayout {
	void generateCard(ICard card, PdfContentByte directContent, float x, float y) throws DocumentException;
	
	ICardReportResourceProvider getResourceProvider();
	
	int getCardWidth();
	
	int getCardHeight();
}
