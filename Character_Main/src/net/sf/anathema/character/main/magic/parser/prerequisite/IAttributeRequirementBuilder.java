package net.sf.anathema.character.main.magic.parser.prerequisite;

import net.sf.anathema.character.main.magic.model.charm.CharmException;
import net.sf.anathema.character.main.magic.model.charm.IndirectCharmRequirement;
import org.dom4j.Element;

public interface IAttributeRequirementBuilder {

  IndirectCharmRequirement[] getCharmAttributeRequirements(Element prerequisitesElement) throws CharmException;

}