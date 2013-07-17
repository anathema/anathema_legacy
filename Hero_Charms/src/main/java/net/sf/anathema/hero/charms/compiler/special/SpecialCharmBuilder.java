package net.sf.anathema.hero.charms.compiler.special;

import net.sf.anathema.character.main.magic.charm.special.ISpecialCharm;
import net.sf.anathema.character.main.magic.parser.dto.special.SpecialCharmDto;

public interface SpecialCharmBuilder {

  ISpecialCharm readCharm(SpecialCharmDto dto);

  boolean supports(SpecialCharmDto dto);
}