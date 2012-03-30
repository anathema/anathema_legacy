package net.sf.anathema.cards.layout;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPRow;
import com.itextpdf.text.pdf.PdfPTable;

public abstract class AbstractCardLayout implements ICardLayout {
	protected void writeText(PdfContentByte directContent, Rectangle rect, Element... body) throws DocumentException {
		ColumnText column = new ColumnText(directContent);
		column.setSimpleColumn(rect);
		for (Element element : body) {
			adjustElement(element);
			column.addElement(element);
		}
		column.go();
	}
	
	private void adjustElement(Element element) {
		if (element instanceof Paragraph) {
			Paragraph paragraph = (Paragraph) element;
			if (paragraph.getChunks().size() > 0) {
				paragraph.setLeading(paragraph.getChunks().get(0).getFont().getSize());
			}
			return;
		}
		if (element instanceof Phrase) {
			Phrase phrase = (Phrase) element;
			phrase.setLeading(phrase.getFont().getSize());
		}
		if (element instanceof PdfPTable) {
			PdfPTable table = (PdfPTable) element;
			for (PdfPRow row : table.getRows()) {
				for (PdfPCell cell : row.getCells()) {
					if (cell.getImage() != null) {
						adjustContentImage(cell.getImage());
					}
				}
			}
		}
		if (element instanceof Image) {
			adjustContentImage((Image) element);
		}
	}
	
	protected void adjustContentImage(Image image) {
		// nothing to do
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
