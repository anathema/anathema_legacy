package net.sf.anathema.character.magic.parser.charms.prerequisite;

import static net.sf.anathema.character.magic.charm.ICharmXMLConstants.ATTRIB_ATTRIBUTE;
import static net.sf.anathema.character.magic.charm.ICharmXMLConstants.ATTRIB_COUNT;
import static net.sf.anathema.character.magic.charm.ICharmXMLConstants.TAG_CHARM_ATTRIBUTE_REQUIREMENT;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.magic.basic.attribute.MagicAttributeImpl;
import net.sf.anathema.character.magic.charm.CharmException;
import net.sf.anathema.character.magic.charm.prerequisite.CharmLearnPrerequisite;
import net.sf.anathema.character.magic.charm.prerequisite.AttributeKnownCharmLearnPrerequisite;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class AttributePrerequisiteBuilder implements IAttributePrerequisiteBuilder {

  @Override
  public AttributeKnownCharmLearnPrerequisite[] getCharmAttributePrerequisites(Element prerequisitesElement) throws CharmException {
    List<CharmLearnPrerequisite> prerequisites = new ArrayList<>();
    for (Element attributeRequirementElement : ElementUtilities.elements(prerequisitesElement, TAG_CHARM_ATTRIBUTE_REQUIREMENT)) {
      prerequisites.add(buildRequirement(attributeRequirementElement));
    }
    return prerequisites.toArray(new AttributeKnownCharmLearnPrerequisite[prerequisites.size()]);
  }

  protected final AttributeKnownCharmLearnPrerequisite buildRequirement(Element attributeRequirementElement) throws CharmException {
    String attributeId = buildId(attributeRequirementElement);
    int requiredCount = buildRequirementCount(attributeRequirementElement);
    return new AttributeKnownCharmLearnPrerequisite(new MagicAttributeImpl(attributeId, false), requiredCount);
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