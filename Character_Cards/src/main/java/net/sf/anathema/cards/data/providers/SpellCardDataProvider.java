package net.sf.anathema.cards.data.providers;

import net.sf.anathema.cards.data.ICardData;
import net.sf.anathema.cards.data.SpellCardData;
import net.sf.anathema.cards.layout.ICardReportResourceProvider;
import net.sf.anathema.character.main.magic.spells.Spell;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.hero.experience.ExperienceModelFetcher;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.spells.model.SpellsModelFetcher;
import net.sf.anathema.hero.spells.sheet.content.SpellStats;
import net.sf.anathema.lib.resources.Resources;

import java.util.ArrayList;
import java.util.List;

public class SpellCardDataProvider extends AbstractMagicCardDataProvider {

  public SpellCardDataProvider(IApplicationModel model, Resources resources) {
    super(model, resources);
  }

  @Override
  public ICardData[] getCards(Hero hero, ICardReportResourceProvider fontProvider) {
    List<ICardData> cards = new ArrayList<>();
    for (Spell spell : getCurrentSpells(hero)) {
      cards.add(new SpellCardData(spell, createSpellStats(spell), getMagicDescription(spell), fontProvider, getResources()));
    }
    return cards.toArray(new ICardData[cards.size()]);
  }

  private Spell[] getCurrentSpells(Hero hero) {
    boolean experienced = ExperienceModelFetcher.fetch(hero).isExperienced();
    return SpellsModelFetcher.fetch(hero).getLearnedSpells(experienced);
  }

  private SpellStats createSpellStats(Spell spell) {
    return new SpellStats(spell);
  }

}
