package net.sf.anathema.character.main.social;

import net.sf.anathema.character.main.IGenericTraitCollection;
import net.sf.anathema.character.main.equipment.ICharacterStatsModifiers;
import net.sf.anathema.character.main.traits.types.AbilityType;

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