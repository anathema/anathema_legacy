package net.sf.anathema.cards.layout;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;

public abstract class AbstractCardLayout implements ICardLayout {
	protected void writeText(PdfContentByte directContent, Rectangle rect, Phrase... phrases) throws DocumentException {
		ColumnText column = new ColumnText(directContent);
		column.setSimpleColumn(rect);
		column.setLeading(phrases[0].getFont().getSize());
		for (Phrase phrase : phrases) {
			column.addText(phrase);
		}
		column.go();
	}
	
	protected void drawImage(PdfContentByte directContent, float x, float y, Image image) {
		try {
			directContent.addImage(image, image.getScaledWidth(), 0, 0, image.getScaledHeight(), x, y - image.getScaledHeight());
		} catch (BadElementException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
	}
}
