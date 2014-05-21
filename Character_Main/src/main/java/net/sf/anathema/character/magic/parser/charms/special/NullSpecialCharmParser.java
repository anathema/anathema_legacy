package net.sf.anathema.character.magic.parser.charms.special;

import net.sf.anathema.character.magic.parser.dto.special.SpecialCharmDto;
import net.sf.anathema.framework.environment.dependencies.DoNotInstantiateAutomatically;
import org.dom4j.Element;

@DoNotInstantiateAutomatically
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
