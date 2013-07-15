package net.sf.anathema.cards.data;

import com.itextpdf.text.Image;

public class LegendEntry {
	private Image icon;
	private String title;
	
	public LegendEntry(Image icon, String title) {
		this.icon = icon;
		this.title = title;
	}
	
	public Image getIcon() {
		return icon;
	}
	
	public String getTitle() {
		return title;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof LegendEntry) {
			LegendEntry otherData = (LegendEntry)obj;
			return this.icon == otherData.icon && this.title.equals(otherData.title);
		}
		return false;
	}
	
	public String toString() {
		return title;
	}
}
