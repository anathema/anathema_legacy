package net.sf.anathema.hero.spells.model;

import net.sf.anathema.hero.model.Hero;

public class SorceryModel extends CircleModel {

  public SorceryModel(Hero hero) {
    super(SpellsModelFetcher.fetch(hero).getSorceryCircles());
  }
}
