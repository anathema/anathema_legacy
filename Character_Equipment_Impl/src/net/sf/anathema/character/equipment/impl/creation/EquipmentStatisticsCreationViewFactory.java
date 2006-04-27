package net.sf.anathema.character.equipment.impl.creation;

import net.sf.anathema.character.equipment.creation.IEquipmentStatisticsCreationViewFactory;
import net.sf.anathema.character.equipment.creation.view.IEquipmentTypeChoiceView;
import net.sf.anathema.character.equipment.impl.creation.view.EquipmentTypeChoiceView;

public class EquipmentStatisticsCreationViewFactory implements IEquipmentStatisticsCreationViewFactory {

  public IEquipmentTypeChoiceView createTypeChoiceView() {
    return new EquipmentTypeChoiceView();
  }
}