package net.sf.anathema.character.equipment.creation.presenter;

import net.sf.anathema.character.equipment.creation.presenter.stats.properties.TraitBoostStatisticsProperties;
import net.sf.anathema.framework.environment.Resources;

import java.awt.Component;

public class ModifierStatisticsPresenter {
  private final ITraitModifyingStatisticsModel modModel;
  private EquipmentStatsView view;
  private final TraitBoostStatisticsProperties properties;

  public ModifierStatisticsPresenter(ITraitModifyingStatisticsModel modModel, EquipmentStatsView view, Resources resources) {
    this.modModel = modModel;
    this.view = view;
    this.properties = new TraitBoostStatisticsProperties(resources);
  }

  public void initPresentation() {
    view.addLabelledComponentRow(
            new String[]{properties.getDDVLabel(), properties.getPDVLabel(), properties.getMDDVLabel(), properties.getMPDVLabel()},
            new Component[]{view.initIntegerSpinner(modModel.getDDVModel()).getComponent(), view.initIntegerSpinner(
                    modModel.getPDVModel()).getComponent(), view.initIntegerSpinner(
                    modModel.getMDDVModel()).getComponent(), view.initIntegerSpinner(
                    modModel.getMPDVModel()).getComponent()});
    view.addLabelledComponentRow(
            new String[]{properties.getMeleeSpeedLabel(), properties.getMeleeAccuracyLabel(), properties.getMeleeDamageLabel(), properties.getMeleeRateLabel()},
            new Component[]{view.initIntegerSpinner(
                    modModel.getMeleeWeaponSpeedModel()).getComponent(), view.initIntegerSpinner(
                    modModel.getMeleeWeaponAccuracyModel()).getComponent(), view.initIntegerSpinner(
                    modModel.getMeleeWeaponDamageModel()).getComponent(), view.initIntegerSpinner(
                    modModel.getMeleeWeaponRateModel()).getComponent()});
    view.addLabelledComponentRow(
            new String[]{properties.getRangedSpeedLabel(), properties.getRangedAccuracyLabel(), properties.getRangedDamageLabel(), properties.getRangedRateLabel()},
            new Component[]{view.initIntegerSpinner(
                    modModel.getRangedWeaponSpeedModel()).getComponent(), view.initIntegerSpinner(
                    modModel.getRangedWeaponAccuracyModel()).getComponent(), view.initIntegerSpinner(
                    modModel.getRangedWeaponDamageModel()).getComponent(), view.initIntegerSpinner(
                    modModel.getRangedWeaponRateModel()).getComponent()});
    view.addLabelledComponentRow(
            new String[]{properties.getJoinBattleLabel(), properties.getJoinDebateLabel(), properties.getJoinWarLabel()},
            new Component[]{view.initIntegerSpinner(
                    modModel.getJoinBattleModel()).getComponent(), view.initIntegerSpinner(
                    modModel.getJoinDebateModel()).getComponent(), view.initIntegerSpinner(
                    modModel.getJoinWarModel()).getComponent()});
  }
}
