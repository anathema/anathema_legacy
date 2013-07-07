package net.sf.anathema.character.main.magic.persistence.builder.prerequisite;

import net.sf.anathema.character.main.magic.CharmAttribute;
import net.sf.anathema.character.main.magic.CharmAttributeRequirement;
import net.sf.anathema.character.main.magic.charms.CharmException;
import net.sf.anathema.character.main.magic.charms.IndirectCharmRequirement;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

import static net.sf.anathema.character.main.magic.ICharmXMLConstants.ATTRIB_ATTRIBUTE;
import static net.sf.anathema.character.main.magic.ICharmXMLConstants.ATTRIB_COUNT;
import static net.sf.anathema.character.main.magic.ICharmXMLConstants.TAG_CHARM_ATTRIBUTE_REQUIREMENT;

public class AttributeRequirementBuilder implements IAttributeRequirementBuilder {

  @Override
  public IndirectCharmRequirement[] getCharmAttributeRequirements(Element prerequisitesElement) throws CharmException {
    List<IndirectCharmRequirement> indirectRequirements = new ArrayList<>();
    for (Element attributeRequirementElement : ElementUtilities.elements(prerequisitesElement, TAG_CHARM_ATTRIBUTE_REQUIREMENT)) {
      indirectRequirements.add(buildRequirement(attributeRequirementElement));
    }
    return indirectRequirements.toArray(new IndirectCharmRequirement[indirectRequirements.size()]);
  }

  protected final IndirectCharmRequirement buildRequirement(Element attributeRequirementElement) throws CharmException {
    String attributeId = buildId(attributeRequirementElement);
    int requiredCount = buildRequirementCount(attributeRequirementElement);
    return new CharmAttributeRequirement(new CharmAttribute(attributeId, false), requiredCount);
  }

  protected String buildId(Element attributeRequirementElement) {
    return attributeRequirementElement.attributeValue(ATTRIB_ATTRIBUTE);
  }

  private int buildRequirementCount(Element attributeRequirementElement) throws CharmException {
    try {
      return ElementUtilities.getIntAttrib(attributeRequirementElement, ATTRIB_COUNT, 1);
    } catch (PersistenceException e) {
      throw new CharmException("Error reading attribute requirement count.", e);
    }
  }
}