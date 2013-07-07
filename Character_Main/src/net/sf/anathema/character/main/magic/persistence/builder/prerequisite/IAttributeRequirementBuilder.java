package net.sf.anathema.character.main.magic.persistence.builder.prerequisite;

import net.sf.anathema.character.main.magic.charms.CharmException;
import net.sf.anathema.character.main.magic.charms.IndirectCharmRequirement;
import org.dom4j.Element;

public interface IAttributeRequirementBuilder {

  IndirectCharmRequirement[] getCharmAttributeRequirements(Element prerequisitesElement) throws CharmException;

}