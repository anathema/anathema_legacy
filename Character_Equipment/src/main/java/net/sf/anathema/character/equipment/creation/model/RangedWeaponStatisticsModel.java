package net.sf.anathema.character.equipment.creation.model;

import net.sf.anathema.character.equipment.creation.presenter.IRangedCombatStatisticsModel;
import net.sf.anathema.character.equipment.creation.presenter.IWeaponTag;
import net.sf.anathema.character.equipment.creation.presenter.IWeaponTagsModel;
import net.sf.anathema.lib.data.Range;
import net.sf.anathema.lib.workflow.intvalue.IIntValueModel;
import net.sf.anathema.lib.workflow.intvalue.RangedIntValueModel;

public class RangedWeaponStatisticsModel extends OffensiveStatisticsModel implements IRangedCombatStatisticsModel {

  private final IIntValueModel rangeModel = new RangedIntValueModel(new Range(1, Integer.MAX_VALUE), 1);
  private final IWeaponTagsModel weaponTagsModel;

  public RangedWeaponStatisticsModel(IIntValueModel speedModel, IWeaponTagsModel weaponTagsModel) {
    super(speedModel);
    this.weaponTagsModel = weaponTagsModel;
    for (IWeaponTag tag : weaponTagsModel.getAllTags()) {
      weaponTagsModel.getSelectedModel(tag).addChangeListener(selected -> announceValidationChange());
    }
  }

  @Override
  public IIntValueModel getRangeModel() {
    return rangeModel;
  }

  @Override
  public boolean supportsRate() {
    return true;
  }

  @Override
  public boolean isValid() {
    return super.isValid() && isRangedWeaponValid();
  }

  @SuppressWarnings("RedundantIfStatement")
  private boolean isRangedWeaponValid() {
    if (!weaponTagsModel.isThrownTypeTagSelected() && weaponTagsModel.isThrownWeaponTagSelected()) {
      return false;
    }
    if (weaponTagsModel.isRangedTypeTagSelected()) {
      return true;
    }
    return false;
  }
}