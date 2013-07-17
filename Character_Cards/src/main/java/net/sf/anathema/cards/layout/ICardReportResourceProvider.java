package net.sf.anathema.cards.layout;

import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import net.sf.anathema.character.main.magic.spells.CircleType;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.character.main.magic.charm.martial.MartialArtsLevel;

public interface ICardReportResourceProvider {
	
	Image getCardBaseImage();
	
	Image getCardStatBlockImage();
	
	Image getCardIconShadowImage();
	
	Image getCardIconBlockImage();
	
	Image getCardBodyBlockImage();
	
	Image getCharacterIcon(CharacterType type);
	
	Image getTraitIcon(TraitType trait);
	
	Image getSpellIcon(CircleType circle);
	
	Image getMartialArtIcon(String groupId);
	
	Image getMartialArtLevelIcon(MartialArtsLevel level);
	
	Image getNullIcon();
	
	Font getTitleFont();
	
	Font getBoldFont();
	
	Font getSymbolFont();
	
	Font getNormalFont();
	
	Font getKeywordFont();
	
	Font getSourceFont();
}
