package net.sf.anathema.character.generic.impl.magic.persistence.builder.prerequisite;

import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_ATTRIBUTE;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_COUNT;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_CHARM_ATTRIBUTE_REQUIREMENT;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.impl.magic.CharmAttribute;
import net.sf.anathema.character.generic.impl.magic.CharmAttributeRequirement;
import net.sf.anathema.character.generic.magic.charms.CharmException;
import net.sf.anathema.character.generic.magic.charms.ICharmAttributeRequirement;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class AttributeRequirementBuilder implements IAttributeRequirementBuilder {

  public ICharmAttributeRequirement[] getCharmAttributeRequirements(Element prerequisitesElement) throws CharmException {
    List<ICharmAttributeRequirement> attributeRequirements = new ArrayList<ICharmAttributeRequirement>();
    for (Element attributeRequirementElement : ElementUtilities.elements(
        prerequisitesElement,
        TAG_CHARM_ATTRIBUTE_REQUIREMENT)) {
      attributeRequirements.add(buildRequirement(attributeRequirementElement));
    }
    return attributeRequirements.toArray(new ICharmAttributeRequirement[attributeRequirements.size()]);
  }

  protected final ICharmAttributeRequirement buildRequirement(Element attributeRequirementElement)
      throws CharmException {
    String attributeId = buildId(attributeRequirementElement);
    int requiredCount = buildRequirementCount(attributeRequirementElement);
    return new CharmAttributeRequirement(new CharmAttribute(attributeId, false), requiredCount);
  }

  protected String buildId(Element attributeRequirementElement) {
    return attributeRequirementElement.attributeValue(ATTRIB_ATTRIBUTE);
  }

  private int buildRequirementCount(Element attributeRequirementElement) throws CharmException {
    int requiredCount;
    try {
      requiredCount = ElementUtilities.getIntAttrib(attributeRequirementElement, ATTRIB_COUNT, 1);
    }
    catch (PersistenceException e) {
      throw new CharmException("Error reading attribute requirement count.", e); //$NON-NLS-1$
    }
    return requiredCount;
  }
}