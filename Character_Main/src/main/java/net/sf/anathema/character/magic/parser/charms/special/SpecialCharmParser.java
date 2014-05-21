package net.sf.anathema.character.magic.parser.charms.special;

import net.sf.anathema.character.magic.parser.dto.special.SpecialCharmDto;
import org.dom4j.Element;

public interface SpecialCharmParser {
  String ATTRIB_NAME = "name";
  String ATTRIB_MODIFIER = "modifier";
  String ATTRIB_TRAIT = "trait";
  String ATTRIB_ESSENCE = "essence";

  void parse(Element charmElement, SpecialCharmDto overallDto);

  boolean supports(Element charmElement);
}
