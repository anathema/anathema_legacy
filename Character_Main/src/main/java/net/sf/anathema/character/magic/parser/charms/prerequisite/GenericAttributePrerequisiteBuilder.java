package net.sf.anathema.character.magic.parser.charms.prerequisite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sf.anathema.character.magic.charm.CharmException;
import net.sf.anathema.character.magic.charm.ICharmXMLConstants;
import net.sf.anathema.character.magic.charm.prerequisite.AttributeKnownCharmLearnPrerequisite;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class GenericAttributePrerequisiteBuilder extends AttributePrerequisiteBuilder {

  private Identifier type;

  @Override
  public AttributeKnownCharmLearnPrerequisite[] getCharmAttributePrerequisites(Element prerequisitesElement) throws CharmException {
    List<AttributeKnownCharmLearnPrerequisite> genericRequirements = new ArrayList<>();
    for (Element attributeRequirementElement : ElementUtilities
            .elements(prerequisitesElement, ICharmXMLConstants.TAG_GENERIC_CHARM_ATTRIBUTE_REQUIREMENT)) {
      genericRequirements.add(buildRequirement(attributeRequirementElement));
    }
    Collections.addAll(genericRequirements, super.getCharmAttributePrerequisites(prerequisitesElement));
    return genericRequirements.toArray(new AttributeKnownCharmLearnPrerequisite[genericRequirements.size()]);
  }

  @Override
  protected String buildId(Element attributeRequirementElement) {
    return super.buildId(attributeRequirementElement) + type.getId();
  }

  public void setType(TraitType type) {
    this.type = type;
  }
}