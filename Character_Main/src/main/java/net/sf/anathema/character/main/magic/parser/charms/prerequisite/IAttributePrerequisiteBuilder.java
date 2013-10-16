package net.sf.anathema.character.main.magic.parser.charms.prerequisite;

import net.sf.anathema.character.main.magic.charm.CharmException;
import net.sf.anathema.character.main.magic.charm.prerequisite.impl.AttributeKnownCharmLearnPrerequisite;

import org.dom4j.Element;

public interface IAttributePrerequisiteBuilder {

  AttributeKnownCharmLearnPrerequisite[] getCharmAttributePrerequisites(Element prerequisitesElement) throws CharmException;

}