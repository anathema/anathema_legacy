package net.sf.anathema.character.equipment.character.model.stats.modification.equipment;

import net.sf.anathema.character.equipment.character.model.stats.modification.StatModifier;
import net.sf.anathema.hero.equipment.model.IWeaponModifiers;

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