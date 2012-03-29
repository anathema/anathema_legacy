package net.sf.anathema.character.equipment.impl.character.model.stats.modification.equipment;

import net.sf.anathema.character.equipment.impl.character.model.stats.modification.StatModifier;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.WeaponStatsType;
import net.sf.anathema.character.generic.equipment.IEquipmentModifiers;

public class EquipmentDamageModifier implements StatModifier {
  private final IEquipmentModifiers modifiers;
  private final WeaponStatsType type;

  public EquipmentDamageModifier(IEquipmentModifiers modifiers, WeaponStatsType type) {
    this.modifiers = modifiers;
    this.type = type;
  }

  @Override
  public int calculate() {
    if (type.isRanged()) {
      return modifiers.getRangedDamageMod();
    }
    return modifiers.getMeleeDamageMod();
  }
}