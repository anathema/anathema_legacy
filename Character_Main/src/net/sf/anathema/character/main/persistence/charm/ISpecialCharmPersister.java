package net.sf.anathema.character.main.persistence.charm;

import net.sf.anathema.character.main.magic.model.charm.special.CharmSpecialsModel;
import net.sf.anathema.lib.exception.PersistenceException;
import org.dom4j.Element;

public interface ISpecialCharmPersister {

  void saveConfiguration(Element specialElement, CharmSpecialsModel specialCharmConfiguration);

  void loadConfiguration(Element specialElement, CharmSpecialsModel specialCharmConfiguration) throws PersistenceException;
}