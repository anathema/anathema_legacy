package net.sf.anathema.character.equipment.impl.wizard.model;

import net.disy.commons.core.model.ObjectSelectionModel;
import net.sf.anathema.character.equipment.impl.model.EquipmentStatisticsType;

public class AddEquipmentStatisticsWizardModel {

  private final ObjectSelectionModel statisticsTypeModel = new ObjectSelectionModel(EquipmentStatisticsType.values());
  private final CloseCombatStatisticsModel closeCombatModel = new CloseCombatStatisticsModel();

  public ObjectSelectionModel getStatisticsTypeModel() {
    return statisticsTypeModel;
  }
  
  public CloseCombatStatisticsModel getCloseCombatStatisticsModel() {
    return closeCombatModel;
  }
}