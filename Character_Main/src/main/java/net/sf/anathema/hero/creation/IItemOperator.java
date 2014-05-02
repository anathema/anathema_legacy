package net.sf.anathema.hero.creation;

import net.sf.anathema.character.main.template.HeroTemplate;
import net.sf.anathema.lib.exception.PersistenceException;

public interface IItemOperator {

  void operate(HeroTemplate template)
      throws PersistenceException;
}