package net.sf.anathema.character.generic.impl.magic.persistence.builder;

import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_ATTRIBUTE;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_COUNT;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_ID;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_THRESHOLD;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_VALUE;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_CHARM_ATTRIBUTE_REQUIREMENT;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_CHARM_REFERENCE;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_ESSENCE;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_SELECTIVE_CHARM_GROUP;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_TRAIT;

import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.generic.impl.magic.CharmAttribute;
import net.sf.anathema.character.generic.impl.magic.CharmAttributeRequirement;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.test.TraitPrerequisiteBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.prerequisite.CharmPrerequisiteList;
import net.sf.anathema.character.generic.impl.magic.persistence.prerequisite.SelectiveCharmGroupTemplate;
import net.sf.anathema.character.generic.magic.charms.CharmException;
import net.sf.anathema.character.generic.magic.charms.ICharmAttributeRequirement;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.generic.traits.types.ValuedTraitType;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class CharmPrerequisiteListBuilder {

  public CharmPrerequisiteList buildPrerequisiteList(Element prerequisiteListElement, String charmId)
      throws PersistenceException {
    IGenericTrait[] allPrerequisites = buildTraitPrerequisites(charmId, prerequisiteListElement);
    IGenericTrait essence = buildEssencePrerequisite(charmId, prerequisiteListElement);
    String[] prerequisiteCharmIDs = buildCharmPrerequisites(prerequisiteListElement, charmId);
    SelectiveCharmGroupTemplate[] selectiveCharmGroups = buildSelectiveCharmGroups(prerequisiteListElement, charmId);
    ICharmAttributeRequirement[] attributeRequirements = getCharmAttributeRequirements(prerequisiteListElement);
    return new CharmPrerequisiteList(
        allPrerequisites,
        essence,
        prerequisiteCharmIDs,
        selectiveCharmGroups,
        attributeRequirements);
  }

  private IGenericTrait[] buildTraitPrerequisites(String id, Element prerequisiteListElement) throws CharmException {
    List<Element> allPrerequisiteTraitList = ElementUtilities.elements(prerequisiteListElement, TAG_TRAIT);
    IGenericTrait[] allPrerequisites = new IGenericTrait[allPrerequisiteTraitList.size()];
    TraitPrerequisiteBuilder traitBuilder = new TraitPrerequisiteBuilder();
    for (int j = 0; j < allPrerequisiteTraitList.size(); j++) {
      try {
        allPrerequisites[j] = traitBuilder.build(allPrerequisiteTraitList.get(j));
      }
      catch (Exception e) {
        throw new CharmException("Bad prerequisites in Charm: " + id, e); //$NON-NLS-1$
      }
    }
    return allPrerequisites;
  }

  private String[] buildCharmPrerequisites(Element parent, String charmId) throws CharmException {
    List<Element> prerequisiteCharmList = ElementUtilities.elements(parent, TAG_CHARM_REFERENCE);
    String[] prerequisiteCharmIDs = new String[prerequisiteCharmList.size()];
    for (int i = 0; i < prerequisiteCharmList.size(); i++) {
      prerequisiteCharmIDs[i] = prerequisiteCharmList.get(i).attributeValue(ATTRIB_ID);
      if (StringUtilities.isNullOrEmpty(prerequisiteCharmIDs[i])) {
        throw new CharmException("Bad id for prerequisite in Charm: " + charmId); //$NON-NLS-1$
      }
    }
    return prerequisiteCharmIDs;
  }

  private IGenericTrait buildEssencePrerequisite(String id, Element prerequisiteListElement) throws CharmException {
    Element essenceElement = prerequisiteListElement.element(TAG_ESSENCE);
    if (essenceElement == null) {
      throw new CharmException("Cannot process Charm without essence prerequisite: " + id); //$NON-NLS-1$
    }
    int minValue;
    try {
      minValue = Integer.parseInt(essenceElement.attributeValue(ATTRIB_VALUE));
    }
    catch (NumberFormatException e) {
      throw new CharmException("Bad value on essence prerequisite in Charm: " + id); //$NON-NLS-1$
    }
    IGenericTrait essence = new ValuedTraitType(OtherTraitType.Essence, minValue);
    return essence;
  }

  private SelectiveCharmGroupTemplate[] buildSelectiveCharmGroups(Element prerequisiteListElement, String charmId)
      throws PersistenceException {
    List<Element> selectiveCharmGroupElements = ElementUtilities.elements(
        prerequisiteListElement,
        TAG_SELECTIVE_CHARM_GROUP);
    SelectiveCharmGroupTemplate[] charmGroups = new SelectiveCharmGroupTemplate[selectiveCharmGroupElements.size()];
    for (int index = 0; index < selectiveCharmGroupElements.size(); index++) {
      Element groupElement = selectiveCharmGroupElements.get(index);
      String[] groupCharmIds = buildCharmPrerequisites(groupElement, charmId);
      int threshold = ElementUtilities.getRequiredIntAttrib(groupElement, ATTRIB_THRESHOLD);
      charmGroups[index] = new SelectiveCharmGroupTemplate(groupCharmIds, threshold);
    }
    return charmGroups;
  }

  private ICharmAttributeRequirement[] getCharmAttributeRequirements(Element prerequisitesElement)
      throws CharmException {
    List<ICharmAttributeRequirement> attributeRequirements = new ArrayList<ICharmAttributeRequirement>();
    for (Element attributeRequirementElement : ElementUtilities.elements(
        prerequisitesElement,
        TAG_CHARM_ATTRIBUTE_REQUIREMENT)) {
      String attributeId = attributeRequirementElement.attributeValue(ATTRIB_ATTRIBUTE);
      int requiredCount;
      try {
        requiredCount = ElementUtilities.getIntAttrib(attributeRequirementElement, ATTRIB_COUNT, 1);
      }
      catch (PersistenceException e) {
        throw new CharmException("Error reading attribute requirement count.", e); //$NON-NLS-1$
      }
      attributeRequirements.add(new CharmAttributeRequirement(new CharmAttribute(attributeId, false), requiredCount));
    }
    return attributeRequirements.toArray(new ICharmAttributeRequirement[attributeRequirements.size()]);
  }
}