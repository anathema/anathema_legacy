package net.sf.anathema.character.main.magic.parser.charms.special;

import net.sf.anathema.character.main.magic.parser.dto.special.SpecialCharmDto;
import org.dom4j.Element;

public class NullSpecialCharmParser implements  SpecialCharmParser {
  @Override
  public void parse(Element charmElement, SpecialCharmDto overallDto) {
    // nothing to do
  }

  @Override
  public boolean supports(Element charmElement) {
    return false;
  }
}
