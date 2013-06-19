package net.sf.anathema.cards.data.providers;

import net.sf.anathema.cards.data.ICardData;
import net.sf.anathema.cards.data.SpellCardData;
import net.sf.anathema.cards.layout.ICardReportResourceProvider;
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.main.model.experience.ExperienceModelFetcher;
import net.sf.anathema.character.main.model.spells.SpellsModelFetcher;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.reporting.pdf.content.stats.magic.SpellStats;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.lib.resources.Resources;

import java.util.ArrayList;
import java.util.List;

public class SpellCardDataProvider extends AbstractMagicCardDataProvider {

  public SpellCardDataProvider(IApplicationModel model, Resources resources) {
    super(model, resources);
  }

  @Override
  public ICardData[] getCards(ICharacter character, ICardReportResourceProvider fontProvider) {
    List<ICardData> cards = new ArrayList<>();
    for (ISpell spell : getCurrentSpells(character)) {
      cards.add(new SpellCardData(spell, createSpellStats(spell), getMagicDescription(spell), fontProvider, getResources()));
    }
    return cards.toArray(new ICardData[cards.size()]);
  }

  private ISpell[] getCurrentSpells(ICharacter character) {
    boolean experienced = ExperienceModelFetcher.fetch(character).isExperienced();
    return SpellsModelFetcher.fetch(character).getLearnedSpells(experienced);
  }

  private SpellStats createSpellStats(ISpell spell) {
    return new SpellStats(spell);
  }

}
