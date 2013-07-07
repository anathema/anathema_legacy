package net.sf.anathema.character.main.persistence.charm;

import net.sf.anathema.character.main.magic.model.charm.special.ISpecialCharmConfiguration;
import net.sf.anathema.lib.exception.PersistenceException;
import org.dom4j.Element;

public interface ISpecialCharmPersister {

  void saveConfiguration(Element specialElement, ISpecialCharmConfiguration specialCharmConfiguration);

  void loadConfiguration(Element specialElement, ISpecialCharmConfiguration specialCharmConfiguration) throws PersistenceException;
}