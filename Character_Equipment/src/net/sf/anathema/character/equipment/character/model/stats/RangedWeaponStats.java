package net.sf.anathema.character.equipment.character.model.stats;

import net.sf.anathema.character.equipment.creation.model.WeaponTag;
import net.sf.anathema.character.main.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.character.main.traits.types.AbilityType;

public class RangedWeaponStats extends AbstractWeaponStats {

  @Override
  public AbilityType getTraitType() {
    return hasTag(WeaponTag.Thrown) ? AbilityType.Thrown : AbilityType.Archery;
  }

  @Override
  public TraitType getDamageTraitType() {
    return hasTag(WeaponTag.FlameType) || hasTag(WeaponTag.FlatDamage) ? null : super.getDamageTraitType();
  }

  @Override
  public int getMobilityPenalty() {
    return 0;
  }

  @Override
  public IEquipmentStats[] getViews() {
    return new IEquipmentStats[]{this};
  }

}