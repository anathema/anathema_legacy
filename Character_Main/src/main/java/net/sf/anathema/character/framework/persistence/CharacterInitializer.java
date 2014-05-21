package net.sf.anathema.character.framework.persistence;

import net.sf.anathema.hero.model.Hero;

public interface CharacterInitializer {
  void initialize(Hero hero);
}