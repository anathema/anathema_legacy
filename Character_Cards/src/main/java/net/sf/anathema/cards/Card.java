package net.sf.anathema.cards;

import com.itextpdf.text.pdf.PdfContentByte;
import net.sf.anathema.cards.data.ICardData;

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
	
	@Override
    public PdfContentByte getPdfContent() {
		return directContent;
	}
	
	@Override
    public float getX() {
		return upperleftX;
	}
	
	@Override
    public float getY() {
		return upperleftY;
	}
	
	@Override
    public ICardData getData() {
		return data;
	}
}
