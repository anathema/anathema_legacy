package net.sf.anathema.character.main.magic.parser.charms.prerequisite;

import net.sf.anathema.character.main.magic.model.charm.ICharmXMLConstants;
import net.sf.anathema.character.main.magic.model.charm.CharmException;
import net.sf.anathema.character.main.magic.model.charm.IndirectCharmRequirement;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.xml.ElementUtilities;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GenericAttributeRequirementBuilder extends AttributeRequirementBuilder {

  private Identifier type;

  @Override
  public IndirectCharmRequirement[] getCharmAttributeRequirements(Element prerequisitesElement) throws CharmException {
    List<IndirectCharmRequirement> genericRequirements = new ArrayList<>();
    for (Element attributeRequirementElement : ElementUtilities
            .elements(prerequisitesElement, ICharmXMLConstants.TAG_GENERIC_CHARM_ATTRIBUTE_REQUIREMENT)) {
      genericRequirements.add(buildRequirement(attributeRequirementElement));
    }
    Collections.addAll(genericRequirements, super.getCharmAttributeRequirements(prerequisitesElement));
    return genericRequirements.toArray(new IndirectCharmRequirement[genericRequirements.size()]);
  }

  @Override
  protected String buildId(Element attributeRequirementElement) {
    return super.buildId(attributeRequirementElement) + type.getId();
  }

  public void setType(TraitType type) {
    this.type = type;
  }
}