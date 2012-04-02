package net.sf.anathema.cards.data.providers;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.cards.data.CharmCardData;
import net.sf.anathema.cards.data.ICardData;
import net.sf.anathema.cards.layout.ICardReportResourceProvider;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.impl.generic.GenericCharacter;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.reporting.pdf.content.stats.magic.CharmStats;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.lib.resources.IResources;

public class CharmCardDataProvider extends AbstractMagicCardDataProvider {

	public CharmCardDataProvider(IAnathemaModel model, IResources resources) {
		super(model, resources);
	}
	
	@Override
	public ICardData[] getCards(ICharacter character, ICardReportResourceProvider fontProvider) {
		List<ICardData> cards = new ArrayList<ICardData>();
		for (ICharm charm : getCurrentCharms(character)) {
			cards.add(new CharmCardData(charm, createCharmStats(character, charm),
					getMagicDescription(charm),
					fontProvider, getResources()));
		}
		return cards.toArray(new ICardData[0]);
	}
	
	private ICharm[] getCurrentCharms(ICharacter character) {
		return character.getStatistics().getCharms().getLearnedCharms(character.getStatistics().isExperienced());
	}
	
	private CharmStats createCharmStats(ICharacter character, ICharm charm) {
		return new CharmStats(charm, createGenericCharacter(character));
	}
	
	private GenericCharacter createGenericCharacter(ICharacter character) {
		return new GenericCharacter(character.getStatistics());
	}

}
