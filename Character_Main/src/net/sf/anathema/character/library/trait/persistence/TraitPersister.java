package net.sf.anathema.character.library.trait.persistence;

import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.library.trait.rules.ITraitRules;
import net.sf.anathema.character.library.trait.subtrait.ISubTrait;
import net.sf.anathema.character.library.trait.subtrait.ISubTraitContainer;
import net.sf.anathema.character.library.trait.visitor.IAggregatedTrait;
import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;
import net.sf.anathema.character.library.trait.visitor.ITraitVisitor;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;
import org.dom4j.Element;

import java.util.List;

public class TraitPersister {
  private final class LoadTraitVisitor implements ITraitVisitor {
    private final Element element;

    private LoadTraitVisitor(Element element) {
      this.element = element;
    }

    @Override
    public void visitAggregatedTrait(IAggregatedTrait visitedTrait) {
      List<Element> subTraitElements = ElementUtilities.elements(element, TAG_SUB_TRAIT);
      if (subTraitElements.size() == 0) {
        restoreDefaultTrait(element, visitedTrait.getFallbackTrait());
      } else {
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

    @Override
    public void visitDefaultTrait(IDefaultTrait visitedTrait) {
      restoreDefaultTrait(element, visitedTrait);
    }
  }

  public static final String TAG_TRAIT_NAME = "traitName";
  public static final String TAG_SUB_TRAIT = "subTrait";
  public static final String ATTRIB_CREATION_VALUE = "creationValue";
  public static final String ATTRIB_EXPERIENCED_VALUE = "experiencedValue";

  public final Element saveTrait(Element parent, String tagName, ITrait trait) {
    final Element traitElement = parent.addElement(tagName);
    trait.accept(new ITraitVisitor() {

      @Override
      public void visitDefaultTrait(IDefaultTrait visitedTrait) {
        saveTrait(traitElement, visitedTrait);
      }

      @Override
      public void visitAggregatedTrait(IAggregatedTrait visitedTrait) {
        for (ISubTrait subTrait : visitedTrait.getSubTraits().getSubTraits()) {
          Element subTraitElement = traitElement.addElement(TAG_SUB_TRAIT);
          subTraitElement.addElement(TAG_TRAIT_NAME).addCDATA(subTrait.getName());
          saveTrait(subTraitElement, subTrait);
        }
      }
    });
    return traitElement;
  }

  private Element saveTrait(Element traitElement, IDefaultTrait trait) {
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

  public final void restoreTrait(Element traitElement, ITrait trait) throws PersistenceException {
    if (traitElement != null) {
      trait.accept(new LoadTraitVisitor(traitElement));
    }
  }

  protected void restoreDefaultTrait(Element traitElement, IDefaultTrait trait) throws PersistenceException {
    trait.setUncheckedCreationValue(ElementUtilities.getRequiredIntAttrib(traitElement, ATTRIB_CREATION_VALUE));
    int experiencedValue = ElementUtilities.getIntAttrib(traitElement, ATTRIB_EXPERIENCED_VALUE, ITraitRules.UNEXPERIENCED);
    if (experiencedValue != ITraitRules.UNEXPERIENCED) {
      trait.setUncheckedExperiencedValue(experiencedValue);
    }
  }
}