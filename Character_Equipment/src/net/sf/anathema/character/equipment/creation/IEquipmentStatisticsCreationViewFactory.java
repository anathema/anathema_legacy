package net.sf.anathema.character.equipment.creation;

import net.sf.anathema.character.equipment.creation.view.IWeaponStatisticsView;
import net.sf.anathema.character.equipment.creation.view.IEquipmentTypeChoiceView;
import net.sf.anathema.character.equipment.creation.view.IWeaponDamageView;
import net.sf.anathema.character.equipment.creation.view.IWeaponTagsView;

public interface IEquipmentStatisticsCreationViewFactory {

  public IEquipmentTypeChoiceView createTypeChoiceView();

  public IWeaponStatisticsView createEquipmentStatisticsView();

  public IWeaponDamageView createWeaponDamageView();

  public IWeaponTagsView createWeaponTagsView();
}