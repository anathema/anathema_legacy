package net.sf.anathema.character.generic.impl.magic.persistence.builder;

import net.sf.anathema.character.generic.magic.charms.CharmException;

import org.dom4j.Element;

public interface IIdStringBuilder {

  public String build(Element element) throws CharmException;

}