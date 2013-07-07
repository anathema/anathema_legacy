package net.sf.anathema.cards.layout;

import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import net.sf.anathema.character.main.magic.model.charm.MartialArtsLevel;
import net.sf.anathema.character.main.magic.model.spells.CircleType;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.character.main.type.ICharacterType;

public interface ICardReportResourceProvider {
	
	Image getCardBaseImage();
	
	Image getCardStatBlockImage();
	
	Image getCardIconShadowImage();
	
	Image getCardIconBlockImage();
	
	Image getCardBodyBlockImage();
	
	Image getCharacterIcon(ICharacterType type);
	
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
