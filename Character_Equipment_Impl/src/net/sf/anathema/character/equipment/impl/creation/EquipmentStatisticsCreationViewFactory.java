package net.sf.anathema.character.equipment.impl.creation;

import net.sf.anathema.character.equipment.creation.IEquipmentStatisticsCreationViewFactory;
import net.sf.anathema.character.equipment.creation.view.ICloseCombatStatisticsView;
import net.sf.anathema.character.equipment.creation.view.IEquipmentTypeChoiceView;
import net.sf.anathema.character.equipment.creation.view.IWeaponDamageView;
import net.sf.anathema.character.equipment.impl.creation.view.CloseCombatStatisticsView;
import net.sf.anathema.character.equipment.impl.creation.view.EquipmentTypeChoiceView;
import net.sf.anathema.character.equipment.impl.creation.view.WeaponDamageView;

public class EquipmentStatisticsCreationViewFactory implements IEquipmentStatisticsCreationViewFactory {

  public IEquipmentTypeChoiceView createTypeChoiceView() {
    return new EquipmentTypeChoiceView();
  }

  public ICloseCombatStatisticsView createCloseCombatStatisticsView() {
    return new CloseCombatStatisticsView();
  }

  public IWeaponDamageView createWeaponDamageView() {
    return new WeaponDamageView();
  }
}