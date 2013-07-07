package net.sf.anathema.character.main.magic.parser.prerequisite;

import net.sf.anathema.character.main.magic.model.charm.CharmException;
import org.dom4j.Element;

public interface ICharmPrerequisiteBuilder {

  String[] buildCharmPrerequisites(Element parent) throws CharmException;
}
