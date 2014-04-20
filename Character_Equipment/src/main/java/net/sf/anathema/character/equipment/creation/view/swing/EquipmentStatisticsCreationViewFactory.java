package net.sf.anathema.character.equipment.creation.view.swing;

public class EquipmentStatisticsCreationViewFactory implements IEquipmentStatisticsCreationViewFactory {

  @Override
  public IWeaponStatisticsView createEquipmentStatisticsView() {
    return new WeaponStatisticsView();
  }

  @Override
  public WeaponDamageView createWeaponDamageView() {
    return new SwingWeaponDamageView();
  }

  @Override
  public IWeaponTagsView createWeaponTagsView() {
    return new WeaponTagsView();
  }
}