package net.sf.anathema.character.equipment.impl.wizard.properties;

import net.sf.anathema.lib.resources.IResources;

public class AddEquipmentStatsticsProperties implements IAddEquipmentStatisticsProperties {

  private final IEquipmentTypeChoiceProperties typeChoiceProperties;
  private final ICloseCombatStatisticsProperties closeCombatProperties;

  public AddEquipmentStatsticsProperties(IResources resources) {
    typeChoiceProperties = new EquipmentTypeChoiceProperties(resources);
    closeCombatProperties = new CloseCombatStatisticsProperties(resources);
  }

  public IEquipmentTypeChoiceProperties getTypeChoiceProperties() {
    return typeChoiceProperties;
  }

  public ICloseCombatStatisticsProperties getCloseCombatProperties() {
    return closeCombatProperties;
  }
}