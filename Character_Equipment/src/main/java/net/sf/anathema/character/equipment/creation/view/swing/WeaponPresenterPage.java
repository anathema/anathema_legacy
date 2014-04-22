package net.sf.anathema.character.equipment.creation.view.swing;

import net.sf.anathema.character.equipment.creation.presenter.IEquipmentStatisticsCreationModel;
import net.sf.anathema.character.equipment.creation.presenter.IOffensiveStatisticsModel;
import net.sf.anathema.character.equipment.creation.presenter.IWeaponTag;
import net.sf.anathema.character.equipment.creation.presenter.IWeaponTagsModel;
import net.sf.anathema.character.equipment.creation.presenter.stats.properties.OffensiveStatisticsProperties;
import net.sf.anathema.character.equipment.item.model.EquipmentStatisticsType;
import net.sf.anathema.lib.control.ChangeListener;

public abstract class WeaponPresenterPage<O extends IOffensiveStatisticsModel, P extends OffensiveStatisticsProperties> extends
        EquipmentStatisticsPresenterPage<O, P> {

  public WeaponPresenterPage(
          P properties,
          IEquipmentStatisticsCreationModel overallModel,
          O pageModel,
          IWeaponStatisticsView view) {
    super(properties, overallModel, pageModel, view);
  }

  @Override
  public void setInputValidListener(ChangeListener inputValidListener) {
    super.setInputValidListener(inputValidListener);
    IWeaponTagsModel weaponTagsModel = getOverallModel().getWeaponTagsModel();
    for (IWeaponTag tag : weaponTagsModel.getAllTags()) {
      weaponTagsModel.getSelectedModel(tag).addChangeListener(new CheckInputListener(new Runnable() {
        @Override
        public void run() {
          inputValidListener.changeOccurred();
        }
      }));
    }
  }

  @Override
  public boolean canFinish() {
    return !isIllegalRangedWeapon();
  }

  private boolean isIllegalRangedWeapon() {
    if (getOverallModel().getEquipmentType() == EquipmentStatisticsType.RangedCombat) {
      IWeaponTagsModel weaponTagsModel = getOverallModel().getWeaponTagsModel();
      if (!weaponTagsModel.isRangedTypeTagSelected()) {
        return true;
      }
      if (!weaponTagsModel.isThrownTypeTagSelected()
              && weaponTagsModel.isThrownWeaponTagSelected()) {
        return true;
      }
    }
    return false;
  }
}