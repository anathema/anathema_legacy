package net.sf.anathema.character.equipment.creation;

import net.sf.anathema.character.equipment.creation.presenter.stats.IEquipmentStatisticsCreationViewFactory;
import net.sf.anathema.character.equipment.creation.view.EquipmentTypeChoiceView;
import net.sf.anathema.character.equipment.creation.view.IEquipmentTypeChoiceView;
import net.sf.anathema.character.equipment.creation.view.IWeaponStatisticsView;
import net.sf.anathema.character.equipment.creation.view.IWeaponTagsView;
import net.sf.anathema.character.equipment.creation.view.SwingWeaponDamageView;
import net.sf.anathema.character.equipment.creation.view.WeaponDamageView;
import net.sf.anathema.character.equipment.creation.view.WeaponStatisticsView;
import net.sf.anathema.character.equipment.creation.view.WeaponTagsView;

public class EquipmentStatisticsCreationViewFactory implements IEquipmentStatisticsCreationViewFactory {

  @Override
  public IEquipmentTypeChoiceView createTypeChoiceView() {
    return new EquipmentTypeChoiceView();
  }

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