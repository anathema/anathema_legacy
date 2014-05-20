package net.sf.anathema.hero.spells.model;

import net.sf.anathema.hero.model.Hero;

public class NecromancyModel extends CircleModel {

  public NecromancyModel(Hero hero) {
    super(SpellsModelFetcher.fetch(hero).getNecromancyCircles());
  }
}
