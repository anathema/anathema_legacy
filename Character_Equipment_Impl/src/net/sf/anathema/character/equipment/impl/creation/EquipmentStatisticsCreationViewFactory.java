package net.sf.anathema.character.equipment.impl.creation;

import net.sf.anathema.character.equipment.creation.presenter.stats.IEquipmentStatisticsCreationViewFactory;
import net.sf.anathema.character.equipment.creation.view.IEquipmentTypeChoiceView;
import net.sf.anathema.character.equipment.creation.view.IWeaponDamageView;
import net.sf.anathema.character.equipment.creation.view.IWeaponStatisticsView;
import net.sf.anathema.character.equipment.creation.view.IWeaponTagsView;
import net.sf.anathema.character.equipment.impl.creation.view.EquipmentTypeChoiceView;
import net.sf.anathema.character.equipment.impl.creation.view.WeaponDamageView;
import net.sf.anathema.character.equipment.impl.creation.view.WeaponStatisticsView;
import net.sf.anathema.character.equipment.impl.creation.view.WeaponTagsView;

public class EquipmentStatisticsCreationViewFactory implements IEquipmentStatisticsCreationViewFactory {

  public IEquipmentTypeChoiceView createTypeChoiceView() {
    return new EquipmentTypeChoiceView();
  }

  public IWeaponStatisticsView createEquipmentStatisticsView() {
    return new WeaponStatisticsView();
  }

  public IWeaponDamageView createWeaponDamageView() {
    return new WeaponDamageView();
  }

  public IWeaponTagsView createWeaponTagsView() {
    return new WeaponTagsView();
  }
}