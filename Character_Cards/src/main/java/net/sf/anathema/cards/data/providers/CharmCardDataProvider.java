package net.sf.anathema.cards.data.providers;

import net.sf.anathema.cards.data.CharmCardData;
import net.sf.anathema.cards.data.ICardData;
import net.sf.anathema.cards.layout.ICardReportResourceProvider;
import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.hero.charms.model.CharmsModelFetcher;
import net.sf.anathema.hero.charms.sheet.content.CharmContentHelper;
import net.sf.anathema.hero.charms.sheet.content.stats.CharmStats;
import net.sf.anathema.hero.experience.ExperienceModelFetcher;
import net.sf.anathema.hero.model.Hero;

import java.util.ArrayList;
import java.util.List;

public class CharmCardDataProvider extends AbstractMagicCardDataProvider {

  public CharmCardDataProvider(IApplicationModel model, Environment environment) {
    super(model, environment);
  }

  @Override
  public ICardData[] getCards(Hero hero, ICardReportResourceProvider fontProvider) {
    List<ICardData> cards = new ArrayList<>();
    for (Charm charm : getCurrentCharms(hero)) {
      cards.add(new CharmCardData(charm, createCharmStats(hero, charm), getMagicDescription(charm), fontProvider, getResources()));
    }
    return cards.toArray(new ICardData[cards.size()]);
  }

  private Charm[] getCurrentCharms(Hero hero) {
    boolean experienced = ExperienceModelFetcher.fetch(hero).isExperienced();
    return CharmsModelFetcher.fetch(hero).getLearnedCharms(experienced);
  }

  private CharmStats createCharmStats(Hero hero, Charm charm) {
    return new CharmStats(charm, new CharmContentHelper(hero));
  }
}