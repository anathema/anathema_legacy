package net.sf.anathema.character.main.magic.persistence.builder.prerequisite;

import net.sf.anathema.character.main.magic.charms.CharmException;
import org.dom4j.Element;

public interface ICharmPrerequisiteBuilder {

  String[] buildCharmPrerequisites(Element parent) throws CharmException;
}
