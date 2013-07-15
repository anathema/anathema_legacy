package net.sf.anathema.cards.data;

import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;

public class EquipmentCardData implements ICardData {

	private final String title;
	private final Paragraph headerText;
	private final Phrase[] bodyText;
	private final Image icon;
	
	public EquipmentCardData(String title, Paragraph headerText, 
			Phrase[] bodyText, Image icon) {
		this.title = title;
		this.headerText = headerText;
		this.bodyText = bodyText;
		this.icon = icon;
	}
	
	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public Image getPrimaryIcon() {
		return icon;
	}

	@Override
	public Image getSecondaryIcon() {
		return null;
	}

	@Override
	public Paragraph getStats() {
		return headerText;
	}

	@Override
	public String getKeywords() {
		return null;
	}

	@Override
	public Element[] getBody(int contentHeight) {
		return bodyText;
	}

	@Override
	public String getSource() {
		return null;
	}

	@Override
	public boolean wantsNewPage() {
		return false;
	}

}
