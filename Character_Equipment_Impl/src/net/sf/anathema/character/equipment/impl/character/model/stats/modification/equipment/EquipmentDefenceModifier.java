package net.sf.anathema.character.equipment.impl.character.model.stats.modification.equipment;

import net.sf.anathema.character.equipment.IEquipmentModifiers;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.StatModifier;

public class EquipmentDefenceModifier implements StatModifier {

  private final IEquipmentModifiers modifiers;

  public EquipmentDefenceModifier(IEquipmentModifiers modifiers) {
    this.modifiers = modifiers;
  }

  @Override
  public int calculate() {
    return modifiers.getPDVMod();
  }
}