package net.sf.anathema.character.main.persistence.charm;

import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.character.main.library.trait.persistence.TraitPersister;
import net.sf.anathema.character.main.library.trait.rules.ITraitRules;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;
import org.dom4j.Element;

public class ExperiencedRestoringTraitPersister extends TraitPersister {

  @Override
  protected void restoreDefaultTrait(Element traitElement, Trait trait) throws PersistenceException {
    int creationValue = ElementUtilities.getRequiredIntAttrib(traitElement, ATTRIB_CREATION_VALUE);
    int experiencedValue = ElementUtilities.getIntAttrib(traitElement, ATTRIB_EXPERIENCED_VALUE, ITraitRules.UNEXPERIENCED);
    trait.setCreationValue(creationValue);
    if (experiencedValue != ITraitRules.UNEXPERIENCED) {
      trait.setExperiencedValue(experiencedValue);
    } else {
      trait.setExperiencedValue(creationValue);
    }
  }
}