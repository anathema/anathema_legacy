package net.sf.anathema.character.generic.impl.social;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.traits.types.AbilityType;

public class PerformanceSocialAttack extends AbstractSocialAttack {
  
  public PerformanceSocialAttack(IGenericCharacter character) {
    super(character);
  }

  public int getRate() {
    return 1;
  }

  public int getSpeed() {
    return 6;
  }

  @Override
  public AbilityType getName() {
    return AbilityType.Performance;
  }
}