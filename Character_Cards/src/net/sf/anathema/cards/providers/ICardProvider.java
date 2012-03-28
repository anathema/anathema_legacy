package net.sf.anathema.cards.providers;

import net.sf.anathema.cards.layout.ICardReportResourceProvider;
import net.sf.anathema.cards.types.ICard;
import net.sf.anathema.character.model.ICharacter;

public interface ICardProvider {
	ICard[] getCards(ICharacter character, ICardReportResourceProvider resourceProvider);
}
