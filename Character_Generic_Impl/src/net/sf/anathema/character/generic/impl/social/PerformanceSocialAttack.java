package net.sf.anathema.character.generic.impl.social;

import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.equipment.ICharacterStatsModifiers;
import net.sf.anathema.character.generic.traits.types.AbilityType;

public class PerformanceSocialAttack extends AbstractSocialAttack {

  public PerformanceSocialAttack(IGenericTraitCollection collection, ICharacterStatsModifiers equipmentModifiers) {
    super(collection, equipmentModifiers);
  }

  @Override
  public int getRate() {
    return 1;
  }

  @Override
  public int getSpeed() {
    return 6;
  }

  @Override
  public AbilityType getName() {
    return AbilityType.Performance;
  }
}