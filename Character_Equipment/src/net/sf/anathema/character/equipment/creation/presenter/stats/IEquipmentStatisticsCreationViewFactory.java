package net.sf.anathema.character.equipment.creation.presenter.stats;

import net.sf.anathema.character.equipment.creation.view.IEquipmentTypeChoiceView;
import net.sf.anathema.character.equipment.creation.view.WeaponDamageView;
import net.sf.anathema.character.equipment.creation.view.IWeaponStatisticsView;
import net.sf.anathema.character.equipment.creation.view.IWeaponTagsView;

public interface IEquipmentStatisticsCreationViewFactory {

  IEquipmentTypeChoiceView createTypeChoiceView();

  IWeaponStatisticsView createEquipmentStatisticsView();

  WeaponDamageView createWeaponDamageView();

  IWeaponTagsView createWeaponTagsView();
}