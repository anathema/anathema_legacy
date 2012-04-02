package net.sf.anathema.cards.data.providers;

import net.sf.anathema.cards.data.ICardData;
import net.sf.anathema.cards.layout.ICardReportResourceProvider;
import net.sf.anathema.character.model.ICharacter;

public interface ICardDataProvider {
	ICardData[] getCards(ICharacter character, ICardReportResourceProvider resourceProvider);
}
