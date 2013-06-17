package net.sf.anathema.character.main.model.description;

import net.sf.anathema.character.main.hero.Hero;

public class HeroDescriptionFetcher {

  public static HeroDescription fetch(Hero hero) {
    return hero.getModel(HeroDescription.ID);
  }
}
