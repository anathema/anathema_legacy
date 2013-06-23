package net.sf.anathema.cards.data.providers;

import net.sf.anathema.cards.data.ICardData;
import net.sf.anathema.cards.layout.ICardReportResourceProvider;
import net.sf.anathema.hero.model.Hero;

public interface ICardDataProvider {

	ICardData[] getCards(Hero hero, ICardReportResourceProvider resourceProvider);
}
