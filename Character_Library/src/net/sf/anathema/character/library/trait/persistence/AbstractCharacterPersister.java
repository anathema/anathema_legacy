package net.sf.anathema.character.library.trait.persistence;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.IModifiableBasicTrait;
import net.sf.anathema.character.library.trait.rules.ITraitRules;
import net.sf.anathema.framework.persistence.AbstractPersister;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class AbstractCharacterPersister extends AbstractPersister {

  public static final String ATTRIB_CREATION_VALUE = "creationValue"; //$NON-NLS-1$
  public static final String ATTRIB_EXPERIENCED_VALUE = "experiencedValue"; //$NON-NLS-1$

  protected final Element saveTrait(Element parent, String tagName, IModifiableBasicTrait trait) {
    Element traitElement = parent.addElement(tagName);
    ElementUtilities.addAttribute(traitElement, ATTRIB_CREATION_VALUE, trait.getCreationValue());
    if (trait.getExperiencedValue() != ITraitRules.UNEXPERIENCED) {
      ElementUtilities.addAttribute(traitElement, ATTRIB_EXPERIENCED_VALUE, trait.getExperiencedValue());
    }
    return traitElement;
  }

  protected final Element restoreTrait(Element parent, String tagName, IModifiableBasicTrait trait) throws PersistenceException {
    Element traitElement = parent.element(tagName);
    restoreTrait(traitElement, trait);
    return traitElement;
  }

  protected final void restoreTrait(Element traitElement, IModifiableBasicTrait trait) throws PersistenceException {
    if (traitElement != null) {
      trait.setCreationValue(ElementUtilities.getRequiredIntAttrib(traitElement, ATTRIB_CREATION_VALUE));
      int experiencedValue = ElementUtilities.getIntAttrib(
          traitElement,
          ATTRIB_EXPERIENCED_VALUE,
          ITraitRules.UNEXPERIENCED);
      if (experiencedValue != ITraitRules.UNEXPERIENCED) {
        trait.setExperiencedValue(experiencedValue);
      }
    }
  }
}