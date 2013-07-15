package net.sf.anathema.character.equipment.character.model.stats.modification.equipment;

import net.sf.anathema.character.equipment.character.model.stats.modification.StatModifier;
import net.sf.anathema.character.equipment.character.model.stats.modification.WeaponStatsType;
import net.sf.anathema.hero.equipment.model.IWeaponModifiers;

public class EquipmentRateModifier implements StatModifier {
  private final IWeaponModifiers modifiers;
  private final WeaponStatsType type;

  public EquipmentRateModifier(IWeaponModifiers modifiers, WeaponStatsType type) {
    this.modifiers = modifiers;
    this.type = type;
  }

  @Override
  public int calculate() {
    if (type.isRanged()) {
      return modifiers.getRangedRateMod();
    }
    return modifiers.getMeleeRateMod();
  }
}