package net.sf.anathema.hero.combat.model.social;

import net.sf.anathema.character.main.util.HeroStatsModifiers;
import net.sf.anathema.character.main.traits.types.AbilityType;
import net.sf.anathema.hero.traits.TraitMap;

public class PresenceSocialAttack extends AbstractSocialAttack {

  public PresenceSocialAttack(TraitMap collection, HeroStatsModifiers equipmentModifiers) {
    super(collection, equipmentModifiers);
  }

  @Override
  public int getRate() {
    return 2;
  }

  @Override
  public int getSpeed() {
    return 4;
  }

  @Override
  public AbilityType getName() {
    return AbilityType.Presence;
  }
}