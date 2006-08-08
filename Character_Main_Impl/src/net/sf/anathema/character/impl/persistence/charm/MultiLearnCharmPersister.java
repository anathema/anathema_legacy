package net.sf.anathema.character.impl.persistence.charm;

import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmConfiguration;
import net.sf.anathema.character.library.trait.IModifiableTrait;
import net.sf.anathema.character.library.trait.persistence.AbstractCharacterPersister;
import net.sf.anathema.character.model.charm.special.IMultiLearnableCharmConfiguration;
import net.sf.anathema.lib.exception.PersistenceException;

import org.dom4j.Element;

public class MultiLearnCharmPersister extends AbstractCharacterPersister implements ISpecialCharmPersister {

  private static final String TAG_LEARN_COUNT = "LearnCount"; //$NON-NLS-1$

  public void saveConfiguration(Element specialElement, ISpecialCharmConfiguration specialCharmConfiguration) {
    IMultiLearnableCharmConfiguration configuration = (IMultiLearnableCharmConfiguration) specialCharmConfiguration;
    IModifiableTrait category = configuration.getCategory();
    saveTrait(specialElement, TAG_LEARN_COUNT, category);
  }

  public void loadConfiguration(Element specialElement, ISpecialCharmConfiguration specialCharmConfiguration)
      throws PersistenceException {
    IMultiLearnableCharmConfiguration configuration = (IMultiLearnableCharmConfiguration) specialCharmConfiguration;
    Element categoryElement = specialElement.element(TAG_LEARN_COUNT);
    if (categoryElement == null) {
      return;
    }
    restoreTrait(categoryElement, configuration.getCategory());
  }
}