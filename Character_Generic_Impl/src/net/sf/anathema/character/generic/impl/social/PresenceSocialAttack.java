package net.sf.anathema.character.generic.impl.social;

import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.traits.types.AbilityType;

public class PresenceSocialAttack extends AbstractSocialAttack {
  
  public PresenceSocialAttack(IGenericTraitCollection collection) {
    super(collection);
  }

  public int getRate() {
    return 2;
  }

  public int getSpeed() {
    return 4;
  }

  @Override
  public AbilityType getName() {
    return AbilityType.Presence;
  }
}