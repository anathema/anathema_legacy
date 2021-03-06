package net.sf.anathema.character.equipment.character.model.stats;

import net.sf.anathema.character.equipment.creation.model.WeaponTag;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IEquipmentStats;
import net.sf.anathema.hero.traits.model.types.AbilityType;

public class MeleeWeaponStats extends AbstractWeaponStats {

  @Override
  public AbilityType getTraitType() {
    return isMartialArtsOnlyWeapon() ? AbilityType.MartialArts : AbilityType.Melee;
  }

  private boolean isMartialArtsOnlyWeapon() {
    return hasTag(WeaponTag.Natural) || hasTag(WeaponTag.ClinchEnhancer);
  }

  @Override
  public int getMobilityPenalty() {
    if (hasTag(WeaponTag.Shield1)) {
      return -1;
    }
    if (hasTag(WeaponTag.Shield2)) {
      return -2;
    }
    return 0;
  }

  @Override
  public IEquipmentStats[] getViews() {
    if ((!hasTag(WeaponTag.MartialArts)) || isMartialArtsOnlyWeapon()) {
      return new IEquipmentStats[]{this};
    }
    IEquipmentStats[] views = new IEquipmentStats[2];
    views[0] = new WeaponStatsDecorator(this, AbilityType.Melee);
    views[1] = new WeaponStatsDecorator(this, AbilityType.MartialArts);
    return views;
  }
}