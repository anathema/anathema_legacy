package net.sf.anathema.character.equipment.creation.presenter;

import net.sf.anathema.character.equipment.creation.presenter.stats.properties.TraitBoostStatisticsProperties;
import net.sf.anathema.framework.environment.Resources;

public class ModifierStatisticsPresenter {
  private final ITraitModifyingStatisticsModel modModel;
  private EquipmentStatsView view;
  private final TraitBoostStatisticsProperties properties;

  public ModifierStatisticsPresenter(ITraitModifyingStatisticsModel modModel, EquipmentStatsView view,
                                     Resources resources) {
    this.modModel = modModel;
    this.view = view;
    this.properties = new TraitBoostStatisticsProperties(resources);
  }

  public void initPresentation() {
    view.addElement(properties.getDDVLabel(), view.initIntegerSpinner(modModel.getDDVModel()).getComponent());
    view.addElement(properties.getPDVLabel(), view.initIntegerSpinner(modModel.getPDVModel()).getComponent());
    view.addElement(properties.getMDDVLabel(), view.initIntegerSpinner(modModel.getMDDVModel()).getComponent());
    view.addElement(properties.getMPDVLabel(), view.initIntegerSpinner(modModel.getMPDVModel()).getComponent());

    view.addElement(properties.getMeleeSpeedLabel(),
            view.initIntegerSpinner(modModel.getMeleeWeaponSpeedModel()).getComponent());
    view.addElement(properties.getMeleeAccuracyLabel(),
            view.initIntegerSpinner(modModel.getMeleeWeaponAccuracyModel()).getComponent());
    view.addElement(properties.getMeleeDamageLabel(),
            view.initIntegerSpinner(modModel.getMeleeWeaponDamageModel()).getComponent());
    view.addElement(properties.getMeleeRateLabel(),
            view.initIntegerSpinner(modModel.getMeleeWeaponRateModel()).getComponent());

    view.addElement(properties.getRangedSpeedLabel(),
            view.initIntegerSpinner(modModel.getRangedWeaponSpeedModel()).getComponent());
    view.addElement(properties.getRangedAccuracyLabel(),
            view.initIntegerSpinner(modModel.getRangedWeaponAccuracyModel()).getComponent());
    view.addElement(properties.getRangedDamageLabel(),
            view.initIntegerSpinner(modModel.getRangedWeaponDamageModel()).getComponent());
    view.addElement(properties.getRangedRateLabel(),
            view.initIntegerSpinner(modModel.getRangedWeaponRateModel()).getComponent());

    view.addElement(properties.getJoinBattleLabel(),
            view.initIntegerSpinner(modModel.getJoinBattleModel()).getComponent());
    view.addElement(properties.getJoinDebateLabel(),
            view.initIntegerSpinner(modModel.getJoinDebateModel()).getComponent());
    view.addElement(properties.getJoinWarLabel(), view.initIntegerSpinner(modModel.getJoinWarModel()).getComponent());
  }
}
