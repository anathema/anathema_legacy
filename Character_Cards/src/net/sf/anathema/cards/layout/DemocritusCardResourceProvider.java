package net.sf.anathema.cards.layout;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;
import net.sf.anathema.character.generic.magic.spells.CircleType;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.lib.gui.icon.ImageProvider;
import net.sf.anathema.lib.gui.image.ImageLoadingException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DemocritusCardResourceProvider implements ICardReportResourceProvider {

  private final String basePath = "democritus_base/";
  private final static String characterPath = "character/";
  private final static String traitPath = "traits/";
  private final static String spellPath = "spell/";
  private final static String martialArtPath = "martial_art/";
  private final static String martialArtLevelPath = "martial_art_level/";
  private final static String cardBackground = "card_base.png";
  private final static String cardStatBlock = "card_stats.png";
  private final static String cardBodyBlock = "card_body.png";
  private final static String cardIconBlock = "card_icon.png";
  private final static String cardIconShadow = "card_icon_shadow.png";
  private final String nullIcon = "null.png";

  private final Map<String, Image> imageMap = new HashMap<>();

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

  @Override
  public Image getCardBaseImage() {
    return getImage(basePath + cardBackground);
  }

  @Override
  public Image getCardStatBlockImage() {
    return getImage(basePath + cardStatBlock);
  }

  @Override
  public Image getCardBodyBlockImage() {
    return getImage(basePath + cardBodyBlock);
  }

  @Override
  public Image getCardIconBlockImage() {
    return getImage(basePath + cardIconBlock);
  }

  @Override
  public Image getCardIconShadowImage() {
    return getImage(basePath + cardIconShadow);
  }

  @Override
  public Image getCharacterIcon(ICharacterType type) {
    return getImage(characterPath + type.getId() + ".png");
  }

  @Override
  public Image getTraitIcon(ITraitType trait) {
    return getImage(traitPath + trait.getId() + ".png");
  }

  @Override
  public Image getSpellIcon(CircleType circle) {
    return getImage(spellPath + circle.getId() + ".png");
  }

  @Override
  public Image getMartialArtLevelIcon(MartialArtsLevel level) {
    return getImage(martialArtLevelPath + level.getId() + ".png");
  }

  @Override
  public Image getMartialArtIcon(String groupId) {
    return getImage(martialArtPath + groupId + ".png");
  }

  @Override
  public Image getNullIcon() {
    return getImage(nullIcon);
  }

  private Image getImage(String filePath) {
    filePath = filePath.toLowerCase();
    try {
      Image image = imageMap.get(filePath);
      if (image == null) {
        java.awt.Image javaImage = new ImageProvider().getImage("icons/" + filePath);
        image = Image.getInstance(javaImage, null);
        imageMap.put(filePath, image);
      }
      return image;
    } catch (ImageLoadingException exception) {
      if (!filePath.equals(nullIcon)) {
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

  @Override
  public Font getTitleFont() {
    return TITLE_FONT;
  }

  @Override
  public Font getBoldFont() {
    return BOLD_FONT;
  }

  @Override
  public Font getNormalFont() {
    return NORMAL_FONT;
  }

  @Override
  public Font getSymbolFont() {
    return SYMBOL_FONT;
  }

  @Override
  public Font getKeywordFont() {
    return KEYWORD_FONT;
  }

  @Override
  public Font getSourceFont() {
    return SOURCE_FONT;
  }
}
