package net.sf.anathema.character.main.description.model;

import net.sf.anathema.character.main.model.Hero;

public class CharacterDescriptionFetcher {

  public static CharacterDescription fetch(Hero hero) {
    return hero.getModel(CharacterDescription.ID);
  }
}
