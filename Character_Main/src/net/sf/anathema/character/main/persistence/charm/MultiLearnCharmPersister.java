package net.sf.anathema.character.main.persistence.charm;

import net.sf.anathema.character.main.magic.model.charm.special.ISpecialCharmConfiguration;
import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.character.main.library.trait.persistence.TraitPersister;
import net.sf.anathema.character.main.magic.model.charm.special.IMultiLearnableCharmConfiguration;
import net.sf.anathema.lib.exception.PersistenceException;
import org.dom4j.Element;

public class MultiLearnCharmPersister implements ISpecialCharmPersister {

  private static final String TAG_LEARN_COUNT = "LearnCount";
  private final TraitPersister traitPersister = new TraitPersister();

  @Override
  public void saveConfiguration(Element specialElement, ISpecialCharmConfiguration specialCharmConfiguration) {
    IMultiLearnableCharmConfiguration configuration = (IMultiLearnableCharmConfiguration) specialCharmConfiguration;
    Trait category = configuration.getCategory();
    traitPersister.saveTrait(specialElement, TAG_LEARN_COUNT, category);
  }

  @Override
  public void loadConfiguration(Element specialElement, ISpecialCharmConfiguration specialCharmConfiguration) throws PersistenceException {
    IMultiLearnableCharmConfiguration configuration = (IMultiLearnableCharmConfiguration) specialCharmConfiguration;
    Element categoryElement = specialElement.element(TAG_LEARN_COUNT);
    if (categoryElement == null) {
      return;
    }
    traitPersister.restoreTrait(categoryElement, configuration.getCategory());
  }
}