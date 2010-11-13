package net.sf.anathema.character.impl.persistence.charm;

import org.dom4j.Element;

import net.sf.anathema.character.library.trait.persistence.TraitPersister;
import net.sf.anathema.character.library.trait.rules.ITraitRules;
import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;

public class ExperiencedRestoringTraitPersister extends TraitPersister {

  @Override
  protected void restoreDefaultTrait(Element traitElement, IDefaultTrait trait) throws PersistenceException {
    int creationValue = ElementUtilities.getRequiredIntAttrib(traitElement, ATTRIB_CREATION_VALUE);
    int experiencedValue = ElementUtilities.getIntAttrib(
        traitElement,
        ATTRIB_EXPERIENCED_VALUE,
        ITraitRules.UNEXPERIENCED);
    trait.setCreationValue(creationValue);
    if (experiencedValue != ITraitRules.UNEXPERIENCED) {
      trait.setExperiencedValue(experiencedValue);
    }
    else {
      trait.setExperiencedValue(creationValue);
    }
  }
}