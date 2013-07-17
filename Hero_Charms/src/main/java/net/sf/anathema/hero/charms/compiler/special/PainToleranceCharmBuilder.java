package net.sf.anathema.hero.charms.compiler.special;

import net.sf.anathema.character.main.magic.charm.special.ISpecialCharm;
import net.sf.anathema.character.main.magic.charm.special.StaticPainToleranceCharm;
import net.sf.anathema.character.main.magic.parser.charms.special.RegisteredSpecialCharmBuilder;
import net.sf.anathema.character.main.magic.parser.charms.special.SpecialCharmBuilder;
import net.sf.anathema.character.main.magic.parser.dto.special.SpecialCharmDto;

@RegisteredSpecialCharmBuilder
public class PainToleranceCharmBuilder implements SpecialCharmBuilder {

  public ISpecialCharm readCharm(SpecialCharmDto overallDto) {
    return new StaticPainToleranceCharm(overallDto.charmId, overallDto.painTolerance);
  }

  @Override
  public boolean supports(SpecialCharmDto overallDto) {
    return overallDto.painTolerance != null;
  }
}