package net.sf.anathema.charmentry.persistence;

import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_ATTRIBUTE;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_COUNT;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_ID;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_VALUE;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_CHARM_ATTRIBUTE_REQUIREMENT;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_CHARM_REFERENCE;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_ESSENCE;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_PREREQUISITE_LIST;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_TRAIT;

import java.util.Set;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.ICharmData;
import net.sf.anathema.character.generic.magic.charms.ICharmAttributeRequirement;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class PrerequisiteWriter {

  public void write(ICharmData charm, Element charmElement) throws PersistenceException {
    Element listElement = charmElement.addElement(TAG_PREREQUISITE_LIST);
    writeEssence(charm, listElement);
    for (IGenericTrait trait : charm.getPrerequisites()) {
      writeTrait(trait, listElement.addElement(TAG_TRAIT));
    }
    writeParentCharms(charm.getParentCharms(), listElement);
    writeRequiredAttributes(charm.getAttributeRequirements(), listElement);
  }

  private void writeRequiredAttributes(ICharmAttributeRequirement[] attributeRequirements, Element listElement) {
    for (ICharmAttributeRequirement requirement : attributeRequirements) {
      Element requirementElement = listElement.addElement(TAG_CHARM_ATTRIBUTE_REQUIREMENT);
      requirementElement.addAttribute(ATTRIB_ATTRIBUTE, requirement.getAttribute().getId());
      ElementUtilities.addAttribute(requirementElement, ATTRIB_COUNT, requirement.getCount());
    }
  }

  private void writeEssence(ICharmData charm, Element listElement) throws PersistenceException {
    IGenericTrait essence = charm.getEssence();
    if (essence == null) {
      throw new PersistenceException("Essence prerequisite not set for Charm: " + charm.getId()); //$NON-NLS-1$
    }
    writeTrait(essence, listElement.addElement(TAG_ESSENCE));
  }

  private void writeTrait(IGenericTrait trait, Element element) {
    element.addAttribute(ATTRIB_ID, trait.getType().getId());
    ElementUtilities.addAttribute(element, ATTRIB_VALUE, trait.getCurrentValue());
  }

  private void writeParentCharms(Set<ICharm> parentCharms, Element listElement) {
    for (ICharm charm : parentCharms) {
      Element referenceElement = listElement.addElement(TAG_CHARM_REFERENCE);
      referenceElement.addAttribute(ATTRIB_ID, charm.getId());
    }
  }
}
