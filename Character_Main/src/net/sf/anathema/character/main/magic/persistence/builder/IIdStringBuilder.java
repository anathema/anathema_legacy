package net.sf.anathema.character.main.magic.persistence.builder;

import net.sf.anathema.character.main.magic.charms.CharmException;
import org.dom4j.Element;

public interface IIdStringBuilder {

  String build(Element element) throws CharmException;
}