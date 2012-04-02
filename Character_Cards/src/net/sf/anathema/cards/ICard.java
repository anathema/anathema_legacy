package net.sf.anathema.cards;

import com.itextpdf.text.pdf.PdfContentByte;

import net.sf.anathema.cards.data.ICardData;

public interface ICard {
	PdfContentByte getPdfContent();
	
	float getX();
	
	float getY();
	
	ICardData getData();
}
