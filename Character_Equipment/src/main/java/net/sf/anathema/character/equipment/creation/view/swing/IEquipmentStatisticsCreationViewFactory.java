package net.sf.anathema.character.equipment.creation.view.swing;

public interface IEquipmentStatisticsCreationViewFactory {

  IWeaponStatisticsView createEquipmentStatisticsView();

  WeaponDamageView createWeaponDamageView();

  IWeaponTagsView createWeaponTagsView();
}