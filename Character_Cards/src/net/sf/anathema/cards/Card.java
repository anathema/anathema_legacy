package net.sf.anathema.cards;

import net.sf.anathema.cards.data.ICardData;

import com.itextpdf.text.pdf.PdfContentByte;

public class Card implements ICard {
	private final PdfContentByte directContent;
	private final ICardData data;
	private final float upperleftX;
	private final float upperleftY;
	
	public Card(PdfContentByte directContent, float upperleftX, float upperleftY, ICardData data) {
		this.directContent = directContent;
		this.upperleftX = upperleftX;
		this.upperleftY = upperleftY;
		this.data = data;
	}
	
	public PdfContentByte getPdfContent() {
		return directContent;
	}
	
	public float getX() {
		return upperleftX;
	}
	
	public float getY() {
		return upperleftY;
	}
	
	public ICardData getData() {
		return data;
	}
}
