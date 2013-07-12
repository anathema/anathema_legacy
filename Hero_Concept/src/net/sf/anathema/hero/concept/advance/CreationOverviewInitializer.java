package net.sf.anathema.hero.concept.advance;

import net.sf.anathema.hero.concept.HeroConcept;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.points.PointModelFetcher;

public class CreationOverviewInitializer {

  public void initialize(final Hero hero, HeroConcept concept) {
    if (concept.getCasteCollection().isEmpty()) {
      return;
    }
    PointModelFetcher.fetch(hero).addToBonusOverview(new CasteOverviewModel(hero));
  }
}
