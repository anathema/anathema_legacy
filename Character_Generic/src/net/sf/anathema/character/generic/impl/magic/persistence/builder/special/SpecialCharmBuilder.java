package net.sf.anathema.character.generic.impl.magic.persistence.builder.special;

import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import org.dom4j.Element;

public interface SpecialCharmBuilder {
  String ATTRIB_NAME = "name";
  String ATTRIB_MODIFIER = "modifier";
  String ATTRIB_TRAIT = "trait";
  String ATTRIB_ESSENCE = "essence";

  ISpecialCharm readCharm(Element charmElement, String id);

  boolean willReadCharm(Element charmElement);
}