package net.sf.anathema.character.main.persistence.charm;

import net.sf.anathema.character.main.magic.model.charm.special.CharmSpecialsModel;
import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.character.main.library.trait.persistence.TraitPersister;
import net.sf.anathema.character.main.magic.model.charm.special.MultiLearnCharmSpecials;
import net.sf.anathema.lib.exception.PersistenceException;
import org.dom4j.Element;

public class MultiLearnCharmPersister implements ISpecialCharmPersister {

  public static final String TAG_LEARN_COUNT = "LearnCount";
  private final TraitPersister traitPersister = new TraitPersister();

  @Override
  public void saveConfiguration(Element specialElement, CharmSpecialsModel specialCharmConfiguration) {
    MultiLearnCharmSpecials configuration = (MultiLearnCharmSpecials) specialCharmConfiguration;
    Trait category = configuration.getCategory();
    traitPersister.saveTrait(specialElement, TAG_LEARN_COUNT, category);
  }

  @Override
  public void loadConfiguration(Element specialElement, CharmSpecialsModel specialCharmConfiguration) throws PersistenceException {
    MultiLearnCharmSpecials configuration = (MultiLearnCharmSpecials) specialCharmConfiguration;
    Element categoryElement = specialElement.element(TAG_LEARN_COUNT);
    if (categoryElement == null) {
      return;
    }
    traitPersister.restoreTrait(categoryElement, configuration.getCategory());
  }
}