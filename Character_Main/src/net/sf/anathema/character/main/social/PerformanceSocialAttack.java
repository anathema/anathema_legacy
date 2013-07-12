package net.sf.anathema.character.main.social;

import net.sf.anathema.character.main.equipment.HeroStatsModifiers;
import net.sf.anathema.character.main.traits.types.AbilityType;
import net.sf.anathema.hero.traits.TraitMap;

public class PerformanceSocialAttack extends AbstractSocialAttack {

  public PerformanceSocialAttack(TraitMap collection, HeroStatsModifiers equipmentModifiers) {
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