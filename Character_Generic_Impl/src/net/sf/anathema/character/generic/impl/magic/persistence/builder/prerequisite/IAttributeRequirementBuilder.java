package net.sf.anathema.character.generic.impl.magic.persistence.builder.prerequisite;

import net.sf.anathema.character.generic.magic.charms.CharmException;
import net.sf.anathema.character.generic.magic.charms.ICharmAttributeRequirement;

import org.dom4j.Element;

public interface IAttributeRequirementBuilder {

  public ICharmAttributeRequirement[] getCharmAttributeRequirements(Element prerequisitesElement) throws CharmException;

}