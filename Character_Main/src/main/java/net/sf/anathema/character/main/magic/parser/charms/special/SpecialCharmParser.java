package net.sf.anathema.character.main.magic.parser.charms.special;

import net.sf.anathema.character.main.magic.parser.dto.special.SpecialCharmDto;
import org.dom4j.Element;

public interface SpecialCharmParser {
  void parse(Element charmElement, SpecialCharmDto overallDto);

  boolean supports(Element charmElement);
}
