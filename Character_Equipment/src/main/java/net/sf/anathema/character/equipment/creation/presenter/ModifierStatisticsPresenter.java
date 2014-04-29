package net.sf.anathema.character.equipment.creation.presenter;

import net.sf.anathema.character.equipment.creation.presenter.stats.properties.TraitBoostStatisticsProperties;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.lib.gui.widgets.IIntegerSpinner;
import net.sf.anathema.lib.workflow.intvalue.IIntValueModel;
import net.sf.anathema.lib.workflow.intvalue.IntValuePresentation;

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
    addSpinner(properties.getDDVLabel(), modModel.getDDVModel());
    addSpinner(properties.getPDVLabel(), modModel.getPDVModel());
    addSpinner(properties.getMDDVLabel(), modModel.getMDDVModel());
    addSpinner(properties.getMPDVLabel(), modModel.getMPDVModel());

    addSpinner(properties.getMeleeSpeedLabel(), modModel.getMeleeWeaponSpeedModel());
    addSpinner(properties.getMeleeAccuracyLabel(), modModel.getMeleeWeaponAccuracyModel());
    addSpinner(properties.getMeleeDamageLabel(), modModel.getMeleeWeaponDamageModel());
    addSpinner(properties.getMeleeRateLabel(), modModel.getMeleeWeaponRateModel());

    addSpinner(properties.getRangedSpeedLabel(), modModel.getRangedWeaponSpeedModel());
    addSpinner(properties.getRangedAccuracyLabel(), modModel.getRangedWeaponAccuracyModel());
    addSpinner(properties.getRangedDamageLabel(), modModel.getRangedWeaponDamageModel());
    addSpinner(properties.getRangedRateLabel(), modModel.getRangedWeaponRateModel());

    addSpinner(properties.getJoinBattleLabel(), modModel.getJoinBattleModel());
    addSpinner(properties.getJoinDebateLabel(), modModel.getJoinDebateModel());
    addSpinner(properties.getJoinWarLabel(), modModel.getJoinWarModel());
  }

  private void addSpinner(String label, IIntValueModel model) {
    IIntegerSpinner spinner = view.addIntegerSpinner(label, model.getValue());
    new IntValuePresentation().initPresentation(spinner, model);
  }
}
