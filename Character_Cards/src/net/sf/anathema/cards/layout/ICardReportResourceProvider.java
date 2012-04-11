package net.sf.anathema.cards.layout;

import com.itextpdf.text.Font;
import com.itextpdf.text.Image;

import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;
import net.sf.anathema.character.generic.magic.spells.CircleType;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.type.ICharacterType;

public interface ICardReportResourceProvider {
	
	Image getCardBaseImage();
	
	Image getCardStatBlockImage();
	
	Image getCardIconShadowImage();
	
	Image getCardIconBlockImage();
	
	Image getCardBodyBlockImage();
	
	Image getCharacterIcon(ICharacterType type);
	
	Image getTraitIcon(ITraitType trait);
	
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
