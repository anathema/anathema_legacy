package net.sf.anathema.hero.charms.compiler.special;

import net.sf.anathema.hero.charms.model.special.ISpecialCharm;
import net.sf.anathema.hero.charms.model.special.subeffects.SubEffectCharm;
import net.sf.anathema.character.main.magic.parser.dto.special.SpecialCharmDto;
import net.sf.anathema.character.main.magic.parser.dto.special.SubEffectDto;

@RegisteredSpecialCharmBuilder
public class SubEffectCharmBuilder implements SpecialCharmBuilder {

  @Override
  public ISpecialCharm readCharm(SpecialCharmDto overallDto) {
    SubEffectDto subEffect = overallDto.subEffect;
    String[] effects = subEffect.subEffects.toArray(new String[subEffect.subEffects.size()]);
    return new SubEffectCharm(overallDto.charmId, effects, subEffect.cost);
  }

  @Override
  public boolean supports(SpecialCharmDto overallDto) {
    return overallDto.subEffect != null;
  }
}