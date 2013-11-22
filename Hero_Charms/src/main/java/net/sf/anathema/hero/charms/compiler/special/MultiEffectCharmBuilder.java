package net.sf.anathema.hero.charms.compiler.special;

import net.sf.anathema.hero.charms.model.special.subeffects.ComplexMultipleEffectCharm;
import net.sf.anathema.hero.charms.model.special.ISpecialCharm;
import net.sf.anathema.character.main.magic.parser.dto.special.MultiEffectDto;
import net.sf.anathema.character.main.magic.parser.dto.special.SpecialCharmDto;

@SuppressWarnings("UnusedDeclaration")
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