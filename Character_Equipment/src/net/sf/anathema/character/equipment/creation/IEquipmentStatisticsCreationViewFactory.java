package net.sf.anathema.character.equipment.creation;

import net.sf.anathema.character.equipment.creation.view.IEquipmentTypeChoiceView;

public interface IEquipmentStatisticsCreationViewFactory {

  public IEquipmentTypeChoiceView createTypeChoiceView();
}