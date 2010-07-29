package net.sf.anathema.character.generic.impl.magic.persistence.builder.prerequisite;

import net.sf.anathema.character.generic.magic.charms.CharmException;

import org.dom4j.Element;

public interface ICharmPrerequisiteBuilder {

  public String[] buildCharmPrerequisites(Element parent) throws CharmException;
}
