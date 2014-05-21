package net.sf.anathema.cards.layout;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import net.sf.anathema.character.magic.spells.CircleType;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.character.framework.type.CharacterType;
import net.sf.anathema.character.magic.charm.martial.MartialArtsLevel;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.icon.ImageLoadingException;
import net.sf.anathema.lib.gui.icon.ImageProvider;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DemocritusCardResourceProvider implements ICardReportResourceProvider {

  private final static String nullIcon = "icons/null.png";

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
    return getImage("democritus_base/card_base.png");
  }

  @Override
  public Image getCardStatBlockImage() {
    return getImage("icons/democritus_base/card_stats.png");
  }

  @Override
  public Image getCardBodyBlockImage() {
    return getImage("icons/democritus_base/card_body.png");
  }

  @Override
  public Image getCardIconBlockImage() {
    return getImage("icons/democritus_base/card_icon.png");
  }

  @Override
  public Image getCardIconShadowImage() {
    return getImage("icons/democritus_base/card_icon_shadow.PNG");
  }

  @Override
  public Image getCharacterIcon(CharacterType type) {
    return getImage("icons/character/" + type.getId() + ".png");
  }

  @Override
  public Image getTraitIcon(TraitType trait) {
    return getImage("icons/traits/" + trait.getId() + ".png");
  }

  @Override
  public Image getSpellIcon(CircleType circle) {
    return getImage("icons/spell/" + circle.getId() + ".png");
  }

  @Override
  public Image getMartialArtLevelIcon(MartialArtsLevel level) {
    return getImage("icons/martial_art_level/" + level.getId() + ".png");
  }

  @Override
  public Image getMartialArtIcon(String groupId) {
    return getImage("icons/martial_art/" + groupId + ".png");
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
        java.awt.Image javaImage = new ImageProvider().getImage(new RelativePath(filePath));
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
