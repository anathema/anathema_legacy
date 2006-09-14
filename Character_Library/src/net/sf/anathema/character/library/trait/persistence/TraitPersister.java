package net.sf.anathema.character.library.trait.persistence;

import java.util.List;

import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.library.trait.rules.ITraitRules;
import net.sf.anathema.character.library.trait.subtrait.ISubTrait;
import net.sf.anathema.character.library.trait.subtrait.ISubTraitContainer;
import net.sf.anathema.character.library.trait.visitor.IAggregatedTrait;
import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;
import net.sf.anathema.character.library.trait.visitor.ITraitVisitor;
import net.sf.anathema.lib.exception.NestedRuntimeException;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class TraitPersister {
  private final class LoadTraitVisitor implements ITraitVisitor {
    private final Element element;

    private LoadTraitVisitor(Element element) {
      this.element = element;
    }

    public void visitAggregatedTrait(IAggregatedTrait visitedTrait) {
      try {
        List<Element> subTraitElements = ElementUtilities.elements(element, TAG_SUB_TRAIT);
        if (subTraitElements.size() == 0) {
          restoreDefaultTrait(element, visitedTrait.getFallbackTrait());
        }
        else {
          ISubTraitContainer container = visitedTrait.getSubTraits();
          for (Element subTraitElement : subTraitElements) {
            String traitName = ElementUtilities.getRequiredText(subTraitElement, TAG_TRAIT_NAME);
            ISubTrait subTrait = container.getSubTrait(traitName);
            if (subTrait == null) {
              subTrait = container.addSubTrait(traitName);
            }
            restoreDefaultTrait(subTraitElement, subTrait);
          }
        }
      }
      catch (PersistenceException e) {
        throw new NestedRuntimeException(e);
      }
    }

    public void visitDefaultTrait(IDefaultTrait visitedTrait) {
      try {
        restoreDefaultTrait(element, visitedTrait);
      }
      catch (PersistenceException e) {
        throw new NestedRuntimeException(e);
      }
    }
  }

  public static final String TAG_TRAIT_NAME = "traitName"; //$NON-NLS-1$
  public static final String TAG_SUB_TRAIT = "subTrait"; //$NON-NLS-1$
  public static final String ATTRIB_CREATION_VALUE = "creationValue"; //$NON-NLS-1$
  public static final String ATTRIB_EXPERIENCED_VALUE = "experiencedValue"; //$NON-NLS-1$

  public final Element saveTrait(Element parent, String tagName, ITrait trait) {
    final Element traitElement = parent.addElement(tagName);
    trait.accept(new ITraitVisitor() {

      public void visitDefaultTrait(IDefaultTrait visitedTrait) {
        saveTrait(traitElement, visitedTrait);
      }

      public void visitAggregatedTrait(IAggregatedTrait visitedTrait) {
        for (ISubTrait subTrait : visitedTrait.getSubTraits().getSubTraits()) {
          final Element subTraitElement = traitElement.addElement(TAG_SUB_TRAIT);
          subTraitElement.addElement(TAG_TRAIT_NAME).addCDATA(subTrait.getName());
          saveTrait(subTraitElement, subTrait);
        }
      }
    });
    return traitElement;
  }

  private final Element saveTrait(Element traitElement, IDefaultTrait trait) {
    ElementUtilities.addAttribute(traitElement, ATTRIB_CREATION_VALUE, trait.getCreationValue());
    if (trait.getExperiencedValue() != ITraitRules.UNEXPERIENCED) {
      ElementUtilities.addAttribute(traitElement, ATTRIB_EXPERIENCED_VALUE, trait.getExperiencedValue());
    }
    return traitElement;
  }

  public final Element restoreTrait(Element parent, String tagName, ITrait trait) throws PersistenceException {
    Element traitElement = parent.element(tagName);
    restoreTrait(traitElement, trait);
    return traitElement;
  }

  public final void restoreTrait(final Element traitElement, ITrait trait) throws PersistenceException {
    if (traitElement != null) {
      try {
        trait.accept(new LoadTraitVisitor(traitElement));
      }
      catch (NestedRuntimeException e) {
        throw (PersistenceException) e.getCause();
      }
    }
  }

  private void restoreDefaultTrait(Element traitElement, IDefaultTrait trait) throws PersistenceException {
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