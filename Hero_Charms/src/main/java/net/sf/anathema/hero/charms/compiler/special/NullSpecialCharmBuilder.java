package net.sf.anathema.hero.charms.compiler.special;

import net.sf.anathema.hero.charms.model.special.ISpecialCharm;
import net.sf.anathema.character.main.magic.parser.dto.special.SpecialCharmDto;

public class NullSpecialCharmBuilder implements SpecialCharmBuilder {
  @Override
  public ISpecialCharm readCharm(SpecialCharmDto dto) {
    return null;
  }

  @Override
  public boolean supports(SpecialCharmDto dto) {
    return false;
  }
}
