package net.sf.anathema.character.main.magic.parser.charms.prerequisite;

import net.sf.anathema.character.main.magic.charm.CharmException;
import net.sf.anathema.character.main.magic.charm.requirements.IndirectCharmRequirement;
import org.dom4j.Element;

public interface IAttributeRequirementBuilder {

  IndirectCharmRequirement[] getCharmAttributeRequirements(Element prerequisitesElement) throws CharmException;

}