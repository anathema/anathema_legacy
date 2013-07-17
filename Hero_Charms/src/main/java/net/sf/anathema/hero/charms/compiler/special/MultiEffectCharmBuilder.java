package net.sf.anathema.hero.charms.compiler.special;

import net.sf.anathema.character.main.magic.charm.special.ComplexMultipleEffectCharm;
import net.sf.anathema.character.main.magic.charm.special.ISpecialCharm;
import net.sf.anathema.character.main.magic.parser.charms.special.RegisteredSpecialCharmBuilder;
import net.sf.anathema.character.main.magic.parser.charms.special.SpecialCharmBuilder;
import net.sf.anathema.character.main.magic.parser.dto.special.MultiEffectDto;
import net.sf.anathema.character.main.magic.parser.dto.special.SpecialCharmDto;

@RegisteredSpecialCharmBuilder
public class MultiEffectCharmBuilder implements SpecialCharmBuilder {

  @Override
  public ISpecialCharm readCharm(SpecialCharmDto overallDto) {
    MultiEffectDto multiEffect = overallDto.multiEffect;
    String[] effects = multiEffect.effects.toArray(new String[multiEffect.effects.size()]);
    return new ComplexMultipleEffectCharm(overallDto.charmId, effects, multiEffect.prerequisiteEffectMap);
  }

  @Override
  public boolean supports(SpecialCharmDto overallDto) {
    return overallDto.multiEffect != null;
  }
}