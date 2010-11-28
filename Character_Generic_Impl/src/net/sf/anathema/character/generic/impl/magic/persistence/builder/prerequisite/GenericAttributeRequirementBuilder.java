package net.sf.anathema.character.generic.impl.magic.persistence.builder.prerequisite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants;
import net.sf.anathema.character.generic.magic.charms.CharmException;
import net.sf.anathema.character.generic.magic.charms.ICharmAttributeRequirement;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class GenericAttributeRequirementBuilder extends AttributeRequirementBuilder {

  private ITraitType type;

  @Override
  public ICharmAttributeRequirement[] getCharmAttributeRequirements(Element prerequisitesElement) throws CharmException {
    List<ICharmAttributeRequirement> genericRequirements = new ArrayList<ICharmAttributeRequirement>();
    for (Element attributeRequirementElement : ElementUtilities.elements(
        prerequisitesElement,
        ICharmXMLConstants.TAG_GENERIC_CHARM_ATTRIBUTE_REQUIREMENT)) {
      genericRequirements.add(buildRequirement(attributeRequirementElement));
    }
    Collections.addAll(genericRequirements, super.getCharmAttributeRequirements(prerequisitesElement));
    return genericRequirements.toArray(new ICharmAttributeRequirement[genericRequirements.size()]);
  }

  @Override
  protected String buildId(Element attributeRequirementElement) {
    return super.buildId(attributeRequirementElement) + type.getId();
  }

  public void setType(ITraitType type) {
    this.type = type;
  }
}