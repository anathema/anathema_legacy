package net.sf.anathema.character.impl.persistence.charm;

import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmConfiguration;
import net.sf.anathema.character.library.trait.persistence.TraitPersister;
import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;
import net.sf.anathema.character.model.charm.special.IMultiLearnableCharmConfiguration;
import net.sf.anathema.lib.exception.PersistenceException;

import org.dom4j.Element;

public class MultiLearnCharmPersister implements ISpecialCharmPersister {

  private static final String TAG_LEARN_COUNT = "LearnCount"; //$NON-NLS-1$
  private final TraitPersister traitPersister = new TraitPersister();

  public void saveConfiguration(Element specialElement, ISpecialCharmConfiguration specialCharmConfiguration) {
    IMultiLearnableCharmConfiguration configuration = (IMultiLearnableCharmConfiguration) specialCharmConfiguration;
    IDefaultTrait category = configuration.getCategory();
    traitPersister.saveTrait(specialElement, TAG_LEARN_COUNT, category);
  }

  public void loadConfiguration(Element specialElement, ISpecialCharmConfiguration specialCharmConfiguration)
      throws PersistenceException {
    IMultiLearnableCharmConfiguration configuration = (IMultiLearnableCharmConfiguration) specialCharmConfiguration;
    Element categoryElement = specialElement.element(TAG_LEARN_COUNT);
    if (categoryElement == null) {
      return;
    }
    traitPersister.restoreTrait(categoryElement, configuration.getCategory());
  }
}