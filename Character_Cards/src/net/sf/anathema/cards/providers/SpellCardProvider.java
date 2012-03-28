package net.sf.anathema.cards.providers;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.cards.layout.ICardReportResourceProvider;
import net.sf.anathema.cards.types.ICard;
import net.sf.anathema.cards.types.SpellCard;
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.reporting.pdf.content.stats.magic.SpellStats;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.lib.resources.IResources;

public class SpellCardProvider extends AbstractMagicCardProvider {

	public SpellCardProvider(IAnathemaModel model, IResources resources) {
		super(model, resources);
	}

	@Override
	public ICard[] getCards(ICharacter character, ICardReportResourceProvider fontProvider) {
		List<ICard> cards = new ArrayList<ICard>();
		for (ISpell spell : getCurrentSpells(character)) {
			cards.add(new SpellCard(spell, createSpellStats(spell),
					getMagicDescription(spell),
					fontProvider, getResources()));
		}
		return cards.toArray(new ICard[0]);
	}
	
	private ISpell[] getCurrentSpells(ICharacter character) {
	  return character.getStatistics().getSpells().getLearnedSpells(character.getStatistics().isExperienced());
	}

	private SpellStats createSpellStats(ISpell spell) {
	  return new SpellStats(spell);
	}

}
