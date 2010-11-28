package net.sf.anathema.character.impl.persistence.charm;

import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmConfiguration;
import net.sf.anathema.lib.exception.PersistenceException;

import org.dom4j.Element;

public interface ISpecialCharmPersister {

  public void saveConfiguration(Element specialElement, ISpecialCharmConfiguration specialCharmConfiguration);

  public void loadConfiguration(Element specialElement, ISpecialCharmConfiguration specialCharmConfiguration)
      throws PersistenceException;
}