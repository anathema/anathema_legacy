package net.sf.anathema.cards.layout;

import java.awt.Image;

import com.itextpdf.text.Font;

import net.sf.anathema.character.generic.magic.spells.CircleType;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.type.ICharacterType;

public interface ICardReportResourceProvider {
	
	Image getCardBaseImage();
	
	Image getCardStatBlockImage();
	
	Image getCardIconBlockImage();
	
	Image getCardBodyBlockImage();
	
	Image getCharacterIcon(ICharacterType type);
	
	Image getTraitIcon(ITraitType trait);
	
	Image getSpellIcon(CircleType circle);
	
	Font getTitleFont();
	
	Font getBoldFont();
	
	Font getNormalFont();
	
	Font getKeywordFont();
	
	Font getSourceFont();
}
