package net.sf.anathema.character.main.magic.parser.charms;

import net.sf.anathema.character.main.magic.charm.CharmException;
import org.dom4j.Element;

public interface IIdStringBuilder {

  String build(Element element) throws CharmException;
}