package net.sf.anathema.cards.data.providers;

import net.sf.anathema.cards.data.ICardData;
import net.sf.anathema.cards.data.SpellCardData;
import net.sf.anathema.cards.layout.ICardReportResourceProvider;
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.reporting.pdf.content.stats.magic.SpellStats;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.lib.resources.IResources;

import java.util.ArrayList;
import java.util.List;

public class SpellCardDataProvider extends AbstractMagicCardDataProvider {

	public SpellCardDataProvider(IAnathemaModel model, IResources resources) {
		super(model, resources);
	}

	@Override
	public ICardData[] getCards(ICharacter character, ICardReportResourceProvider fontProvider) {
		List<ICardData> cards = new ArrayList<ICardData>();
		for (ISpell spell : getCurrentSpells(character)) {
			cards.add(new SpellCardData(spell, createSpellStats(spell),
					getMagicDescription(spell),
					fontProvider, getResources()));
		}
		return cards.toArray(new ICardData[0]);
	}
	
	private ISpell[] getCurrentSpells(ICharacter character) {
	  return character.getSpells().getLearnedSpells(character.isExperienced());
	}

	private SpellStats createSpellStats(ISpell spell) {
	  return new SpellStats(spell);
	}

}
