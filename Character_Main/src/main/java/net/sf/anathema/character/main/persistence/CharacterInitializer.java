package net.sf.anathema.character.main.persistence;

import net.sf.anathema.hero.model.Hero;

public interface CharacterInitializer {
  void initialize(Hero hero);
}