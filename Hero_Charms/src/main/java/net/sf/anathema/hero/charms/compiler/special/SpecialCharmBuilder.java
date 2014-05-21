package net.sf.anathema.hero.charms.compiler.special;

import net.sf.anathema.hero.charms.model.special.ISpecialCharm;
import net.sf.anathema.character.magic.parser.dto.special.SpecialCharmDto;

public interface SpecialCharmBuilder {

  ISpecialCharm readCharm(SpecialCharmDto dto);

  boolean supports(SpecialCharmDto dto);
}