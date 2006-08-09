package net.sf.anathema.character.library.trait.persistence;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.IBasicTrait;
import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.library.trait.rules.ITraitRules;
import net.sf.anathema.character.library.trait.subtrait.ISubTraitContainer;
import net.sf.anathema.character.library.trait.visitor.IAggregatedTrait;
import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;
import net.sf.anathema.character.library.trait.visitor.ITraitVisitor;
import net.sf.anathema.framework.persistence.AbstractPersister;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class AbstractCharacterPersister extends AbstractPersister {

  public static final String ATTRIB_CREATION_VALUE = "creationValue"; //$NON-NLS-1$
  public static final String ATTRIB_EXPERIENCED_VALUE = "experiencedValue"; //$NON-NLS-1$

  protected final Element saveTrait(Element parent, String tagName, IBasicTrait trait) {
    Element traitElement = parent.addElement(tagName);
    ElementUtilities.addAttribute(traitElement, ATTRIB_CREATION_VALUE, trait.getCreationValue());
    if (trait.getExperiencedValue() != ITraitRules.UNEXPERIENCED) {
      ElementUtilities.addAttribute(traitElement, ATTRIB_EXPERIENCED_VALUE, trait.getExperiencedValue());
    }
    return traitElement;
  }

  protected final Element restoreTrait(Element parent, String tagName, ITrait trait) throws PersistenceException {
    Element traitElement = parent.element(tagName);
    restoreTrait(traitElement, trait);
    return traitElement;
  }

  protected final void restoreTrait(final Element traitElement, ITrait trait) throws PersistenceException {
    if (traitElement != null) {
      final IDefaultTrait[] modifiableTrait = new IDefaultTrait[1];
      trait.accept(new ITraitVisitor() {

        public void visitAggregatedTrait(IAggregatedTrait visitedTrait) {
          ISubTraitContainer container = visitedTrait.getSubTraits();
          modifiableTrait[0] = container.getSubTraits()[0];
        }

        public void visitDefaultTrait(IDefaultTrait visitedTrait) {
          modifiableTrait[0] = visitedTrait;
        }
      });
      restoreModifiableTrait(traitElement, modifiableTrait[0]);
    }
  }

  private void restoreModifiableTrait(Element traitElement, IDefaultTrait trait) throws PersistenceException {
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