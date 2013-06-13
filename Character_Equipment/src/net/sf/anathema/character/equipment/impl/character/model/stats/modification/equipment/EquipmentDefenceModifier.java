package net.sf.anathema.character.equipment.impl.character.model.stats.modification.equipment;

import net.sf.anathema.character.equipment.IWeaponModifiers;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.StatModifier;

public class EquipmentDefenceModifier implements StatModifier {

  private final IWeaponModifiers modifiers;

  public EquipmentDefenceModifier(IWeaponModifiers modifiers) {
    this.modifiers = modifiers;
  }

  @Override
  public int calculate() {
    return modifiers.getPDVPoolMod();
  }
}