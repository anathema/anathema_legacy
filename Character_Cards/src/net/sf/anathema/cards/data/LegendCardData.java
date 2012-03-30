package net.sf.anathema.cards.data;

import net.sf.anathema.cards.layout.ICardReportResourceProvider;

import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class LegendCardData implements ICardData {

	public static final int ICONS_PER_CARD = 5;
	
	private final String title;
	private final LegendEntry[] entries;
	private final ICardReportResourceProvider provider;
	
	public LegendCardData(ICardReportResourceProvider provider,
			String title, LegendEntry[] entries) {
		this.title = title;
		this.entries = entries;
		this.provider = provider;
	}
	
	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public Image getPrimaryIcon() {
		return null;
	}

	@Override
	public Image getSecondaryIcon() {
		return null;
	}

	@Override
	public Phrase[] getStats() {
		return new Phrase[0];
	}

	@Override
	public String getKeywords() {
		return null;
	}

	@Override
	public Element[] getBody(int contentHeight) {
		PdfPTable table = new PdfPTable(2);
		int rowHeight = contentHeight / ICONS_PER_CARD;
		for (LegendEntry entry : entries) {
			PdfPCell imageCell = new PdfPCell();
			PdfPCell textCell = new PdfPCell(new Phrase(entry.getTitle(), provider.getTitleFont()));
			imageCell.setFixedHeight(rowHeight);
			imageCell.setImage(entry.getIcon());
			imageCell.setBorder(PdfPCell.NO_BORDER);
			textCell.setBorder(PdfPCell.NO_BORDER);
			textCell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			table.addCell(imageCell);
			table.addCell(textCell);
		}
		
		return new Element[] { table };
	}

	@Override
	public String getSource() {
		return null;
	}

}
