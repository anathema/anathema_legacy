package net.sf.anathema.character.generic.impl.social;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.traits.types.AbilityType;

public class InvestigationSocialAttack extends AbstractSocialAttack {
  
  public InvestigationSocialAttack(IGenericCharacter character) {
    super(character);
  }

  public int getRate() {
    return 2;
  }

  public int getSpeed() {
    return 5;
  }

  @Override
  public AbilityType getName() {
    return AbilityType.Investigation;
  }
}