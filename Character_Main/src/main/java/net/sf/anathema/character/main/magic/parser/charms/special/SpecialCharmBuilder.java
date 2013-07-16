package net.sf.anathema.character.main.magic.parser.charms.special;

import net.sf.anathema.character.main.magic.charm.special.ISpecialCharm;
import org.dom4j.Element;

public interface SpecialCharmBuilder {

  ISpecialCharm readCharm(Element charmElement, String id);

  boolean supports(Element charmElement);
}