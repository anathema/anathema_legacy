package net.sf.anathema.character.main.library.trait.persistence;

import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.character.main.library.trait.rules.ITraitRules;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;
import org.dom4j.Element;

public class TraitPersister {

  public static final String TAG_TRAIT_NAME = "traitName";
  public static final String TAG_SUB_TRAIT = "subTrait";
  public static final String ATTRIB_CREATION_VALUE = "creationValue";
  public static final String ATTRIB_EXPERIENCED_VALUE = "experiencedValue";

  public final Element saveTrait(Element parent, String tagName, Trait trait) {
    final Element traitElement = parent.addElement(tagName);
    saveTrait(traitElement, trait);
    return traitElement;
  }

  private Element saveTrait(Element traitElement, Trait trait) {
    ElementUtilities.addAttribute(traitElement, ATTRIB_CREATION_VALUE, trait.getCreationValue());
    if (trait.getExperiencedValue() != ITraitRules.UNEXPERIENCED) {
      ElementUtilities.addAttribute(traitElement, ATTRIB_EXPERIENCED_VALUE, trait.getExperiencedValue());
    }
    return traitElement;
  }

  public final Element restoreTrait(Element parent, String tagName, Trait trait) throws PersistenceException {
    Element traitElement = parent.element(tagName);
    restoreTrait(traitElement, trait);
    return traitElement;
  }

  public final void restoreTrait(Element traitElement, Trait trait) throws PersistenceException {
    if (traitElement != null) {
      restoreDefaultTrait(traitElement, trait);
    }
  }

  protected void restoreDefaultTrait(Element traitElement, Trait trait) throws PersistenceException {
    trait.setUncheckedCreationValue(ElementUtilities.getRequiredIntAttrib(traitElement, ATTRIB_CREATION_VALUE));
    int experiencedValue = ElementUtilities.getIntAttrib(traitElement, ATTRIB_EXPERIENCED_VALUE, ITraitRules.UNEXPERIENCED);
    if (experiencedValue != ITraitRules.UNEXPERIENCED) {
      trait.setUncheckedExperiencedValue(experiencedValue);
    }
  }
}