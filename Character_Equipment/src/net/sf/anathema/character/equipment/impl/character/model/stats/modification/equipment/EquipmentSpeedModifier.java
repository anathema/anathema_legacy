package net.sf.anathema.character.equipment.impl.character.model.stats.modification.equipment;

import net.sf.anathema.character.equipment.IWeaponModifiers;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.StatModifier;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.WeaponStatsType;

public class EquipmentSpeedModifier implements StatModifier {
  private final IWeaponModifiers modifiers;
  private final WeaponStatsType type;

  public EquipmentSpeedModifier(IWeaponModifiers modifiers, WeaponStatsType type) {
    this.modifiers = modifiers;
    this.type = type;
  }

  @Override
  public int calculate() {
    int modifier;
    if (type.isRanged()) {
      modifier = modifiers.getRangedSpeedMod();
    } else {
      modifier = modifiers.getMeleeSpeedMod();
    }
    return -modifier;
  }
}