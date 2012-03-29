package net.sf.anathema.character.equipment.impl.character.model.stats.modification.equipment;

import net.sf.anathema.character.equipment.impl.character.model.stats.modification.StatModifier;
import net.sf.anathema.character.generic.equipment.IEquipmentModifiers;

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