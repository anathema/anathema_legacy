package net.sf.anathema.character.equipment.impl.character.model.stats.modification.equipment;

import net.sf.anathema.character.equipment.IEquipmentModifiers;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.StatModifier;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.WeaponStatsType;

public class EquipmentSpeedModifier implements StatModifier {
  private final IEquipmentModifiers modifiers;
  private final WeaponStatsType type;

  public EquipmentSpeedModifier(IEquipmentModifiers modifiers, WeaponStatsType type) {
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