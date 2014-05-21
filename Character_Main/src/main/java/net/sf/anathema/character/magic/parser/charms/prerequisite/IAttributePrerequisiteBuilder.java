package net.sf.anathema.character.magic.parser.charms.prerequisite;

import net.sf.anathema.character.magic.charm.CharmException;
import net.sf.anathema.character.magic.charm.prerequisite.AttributeKnownCharmLearnPrerequisite;

import org.dom4j.Element;

public interface IAttributePrerequisiteBuilder {

  AttributeKnownCharmLearnPrerequisite[] getCharmAttributePrerequisites(Element prerequisitesElement) throws CharmException;

}