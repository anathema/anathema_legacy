package net.sf.anathema.cards.reporting;

import java.awt.Image;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;

import net.disy.commons.swing.image.ImageLoadingException;
import net.sf.anathema.character.generic.magic.spells.CircleType;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.lib.resources.IResources;

public class CardReportProperties implements ICardReportProperties {
	private IResources resources;
	
	private final String graphicsPath = "";
	private final String cardBackground = "card_base.png";
	private final String cardStatBlock = "card_stats.png";
	private final String cardBodyBlock = "card_body.png";
	private final String cardIconBlock = "card_icon.png";
	
	private final int MAGIC_TITLE_FONT_SIZE = 8;
	private final int MAGIC_NORMAL_FONT_SIZE = 8;
	private final int MAGIC_SMALL_FONT_SIZE = 6;
	private final int MAGIC_TINY_FONT_SIZE = 4;
	
	private final Font TITLE_FONT =
			new Font(Font.FontFamily.HELVETICA, MAGIC_TITLE_FONT_SIZE, Font.BOLD, BaseColor.BLACK);
	private final Font BOLD_FONT =
			new Font(Font.FontFamily.HELVETICA, MAGIC_NORMAL_FONT_SIZE, Font.BOLD, BaseColor.BLACK);
	private final Font NORMAL_FONT =
			new Font(Font.FontFamily.HELVETICA, MAGIC_NORMAL_FONT_SIZE, Font.NORMAL, BaseColor.BLACK);
	private final Font KEYWORD_FONT =
			new Font(Font.FontFamily.HELVETICA, MAGIC_SMALL_FONT_SIZE, Font.NORMAL, BaseColor.BLACK);
	private final Font SOURCE_FONT =
			new Font(Font.FontFamily.HELVETICA, MAGIC_TINY_FONT_SIZE, Font.ITALIC, BaseColor.BLACK);
	
	public CardReportProperties(IResources resources) {
		this.resources = resources;
	}
	
	public String getString(String id) {
		return resources.getString(id);
	}
	
	public Image getCardBaseImage() {
		return resources.getImage(this.getClass(), graphicsPath + cardBackground);
	}
	
	public Image getCardStatBlockImage() {
		return resources.getImage(this.getClass(), graphicsPath + cardStatBlock);
	}
	
	public Image getCardBodyBlockImage() {
		return resources.getImage(this.getClass(), graphicsPath + cardBodyBlock);
	}
	
	public Image getCardIconBlockImage() {
		return resources.getImage(this.getClass(), graphicsPath + cardIconBlock);
	}
	
	public Image getCharacterIcon(ICharacterType type) {
		try {
			return resources.getImage(this.getClass(), graphicsPath + "character_" + type.getId() + ".png");
		}
		catch (ImageLoadingException exception) {
			return null;
		}
	}
	
	public Image getTraitIcon(ITraitType trait) {
		try {
			return resources.getImage(this.getClass(), graphicsPath + "trait_" + trait.getId() + ".png");
		}
		catch (ImageLoadingException exception) {
			return null;
		}
	}
	
	public Image getSpellIcon(CircleType circle) {
		try {
			return resources.getImage(this.getClass(), graphicsPath + "spells_" + circle.getId() + ".png");
		}
		catch (ImageLoadingException exception) {
			return null;
		}
	}
	
	public Font getTitleFont() {
		return TITLE_FONT;
	}
	
	public Font getBoldFont() {
		return BOLD_FONT;
	}
	
	public Font getNormalFont() {
		return NORMAL_FONT;
	}
	
	public Font getKeywordFont() {
		return KEYWORD_FONT;
	}
	
	public Font getSourceFont() {
		return SOURCE_FONT;
	}
}
