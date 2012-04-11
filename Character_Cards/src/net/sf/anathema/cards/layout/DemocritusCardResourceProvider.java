package net.sf.anathema.cards.layout;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;

import net.disy.commons.swing.image.ImageLoadingException;
import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;
import net.sf.anathema.character.generic.magic.spells.CircleType;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.lib.resources.IResources;

public class DemocritusCardResourceProvider implements ICardReportResourceProvider {
	private IResources resources;
	
	private final String basePath = "democritus_base/";
	private final String characterPath = "character/";
	private final String traitPath = "traits/";
	private final String spellPath = "spell/";
	private final String martialArtPath = "martial_art/";
	private final String martialArtLevelPath = "martial_art_level/";
	private final String cardBackground = "card_base.png";
	private final String cardStatBlock = "card_stats.png";
	private final String cardBodyBlock = "card_body.png";
	private final String cardIconBlock = "card_icon.png";
	private final String cardIconShadow = "card_icon_shadow.png";
	private final String nullIcon = "null.png";
	
	private final Map<String, Image> imageMap = new HashMap<String, Image>(); 
	
	private final int MAGIC_TITLE_FONT_SIZE = 8;
	private final int MAGIC_NORMAL_FONT_SIZE = 8;
	private final int MAGIC_SMALL_FONT_SIZE = 6;
	private final int MAGIC_TINY_FONT_SIZE = 4;
	
	private final Font TITLE_FONT =
			new Font(Font.FontFamily.TIMES_ROMAN, MAGIC_TITLE_FONT_SIZE, Font.BOLD, BaseColor.BLACK);
	private final Font BOLD_FONT =
			new Font(Font.FontFamily.HELVETICA, MAGIC_NORMAL_FONT_SIZE, Font.BOLD, BaseColor.BLACK);
	private final Font NORMAL_FONT =
			new Font(Font.FontFamily.TIMES_ROMAN, MAGIC_NORMAL_FONT_SIZE, Font.NORMAL, BaseColor.BLACK);
	private final Font SYMBOL_FONT =
			new Font(Font.FontFamily.SYMBOL, MAGIC_NORMAL_FONT_SIZE, Font.NORMAL, BaseColor.BLACK);
	private final Font KEYWORD_FONT =
			new Font(Font.FontFamily.HELVETICA, MAGIC_SMALL_FONT_SIZE, Font.NORMAL, BaseColor.BLACK);
	private final Font SOURCE_FONT =
			new Font(Font.FontFamily.HELVETICA, MAGIC_TINY_FONT_SIZE, Font.ITALIC, BaseColor.BLACK);
	
	public DemocritusCardResourceProvider(IResources resources) {
		this.resources = resources;
	}
	
	public String getString(String id) {
		return resources.getString(id);
	}
	
	public Image getCardBaseImage() {
		return getImage(basePath + cardBackground);
	}
	
	public Image getCardStatBlockImage() {
		return getImage(basePath + cardStatBlock);
	}
	
	public Image getCardBodyBlockImage() {
		return getImage(basePath + cardBodyBlock);
	}
	
	public Image getCardIconBlockImage() {
		return getImage(basePath + cardIconBlock);
	}
	
	public Image getCardIconShadowImage() {
		return getImage(basePath + cardIconShadow);
	}
	
	public Image getCharacterIcon(ICharacterType type) {
		return getImage(characterPath + type.getId() + ".png");
	}
	
	public Image getTraitIcon(ITraitType trait) {
		return getImage(traitPath + trait.getId() + ".png");
	}
	
	public Image getSpellIcon(CircleType circle) {
		return getImage(spellPath + circle.getId() + ".png");
	}
	
	public Image getMartialArtLevelIcon(MartialArtsLevel level) {
		return getImage(martialArtLevelPath + level.getId() + ".png");
	}
	
	public Image getMartialArtIcon(String groupId) {
		return getImage(martialArtPath + groupId + ".png");
	}
	
	public Image getNullIcon() {
		return getImage(nullIcon);
	}
	
	private Image getImage(String filePath) {
		filePath = filePath.toLowerCase();
		try {
			Image image = imageMap.get(filePath); 
			if (image == null) {
				java.awt.Image javaImage = resources.getImage(this.getClass(), filePath); 
				image = Image.getInstance(javaImage, null);
				imageMap.put(filePath, image);
			}
			return image;
		}
		catch (ImageLoadingException exception) {
			if (filePath != nullIcon) {
				return getNullIcon();
			} else {
				return null;	
			}
		} catch (BadElementException e) {
			return null;
		} catch (IOException e) {
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
	
	public Font getSymbolFont() {
		return SYMBOL_FONT;
	}
	
	public Font getKeywordFont() {
		return KEYWORD_FONT;
	}
	
	public Font getSourceFont() {
		return SOURCE_FONT;
	}
}
