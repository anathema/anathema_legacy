package net.sf.anathema.character.magic.parser.charms.prerequisite;

import net.sf.anathema.character.magic.charm.CharmException;
import org.dom4j.Element;

public interface ICharmPrerequisiteBuilder {

  String[] buildCharmPrerequisites(Element parent) throws CharmException;
}
