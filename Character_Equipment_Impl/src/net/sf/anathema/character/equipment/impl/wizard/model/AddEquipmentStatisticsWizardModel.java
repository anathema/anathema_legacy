package net.sf.anathema.character.equipment.impl.wizard.model;

import net.disy.commons.core.model.ObjectSelectionModel;
import net.sf.anathema.character.equipment.impl.model.EquipmentStatisticsType;

public class AddEquipmentStatisticsWizardModel {

  private ObjectSelectionModel statisticsTypeModel = new ObjectSelectionModel(EquipmentStatisticsType.values());

  public ObjectSelectionModel getStatisticsTypeModel() {
    return statisticsTypeModel;
  }
}