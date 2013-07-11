package net.sf.anathema.character.main.persistence.charm;

import net.sf.anathema.character.main.magic.model.charm.special.ISpecialCharmConfiguration;
import net.sf.anathema.character.main.magic.model.charm.special.TraitCapModifyingCharmConfiguration;
import net.sf.anathema.lib.exception.PersistenceException;
import org.dom4j.Element;

public class TraitCapModifyingCharmPersister implements ISpecialCharmPersister {

  @Override
  public void loadConfiguration(Element specialElement, ISpecialCharmConfiguration specialCharmConfiguration) throws PersistenceException {
    ((TraitCapModifyingCharmConfiguration) specialCharmConfiguration).applyModifier();
  }

  @Override
  public void saveConfiguration(Element specialElement, ISpecialCharmConfiguration specialCharmConfiguration) {
    // nothing to do
  }
}
