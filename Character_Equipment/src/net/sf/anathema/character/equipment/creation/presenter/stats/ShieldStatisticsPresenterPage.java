package net.sf.anathema.character.equipment.creation.presenter.stats;

import java.awt.Component;

import net.sf.anathema.character.equipment.creation.model.stats.IEquipmentStatisticsCreationModel;
import net.sf.anathema.character.equipment.creation.model.stats.IShieldStatisticsModel;
import net.sf.anathema.character.equipment.creation.presenter.stats.properties.ShieldStatisticsProperties;
import net.sf.anathema.lib.resources.IResources;

public class ShieldStatisticsPresenterPage extends
    AbstractEquipmentStatisticsPresenterPage<IShieldStatisticsModel, ShieldStatisticsProperties> {

  public ShieldStatisticsPresenterPage(
      IResources resources,
      IEquipmentStatisticsCreationModel model,
      IEquipmentStatisticsCreationViewFactory viewFactory) {
    super(resources, new ShieldStatisticsProperties(resources), model, model.getShieldStatisticsModel(), viewFactory);
  }

  @Override
  protected void addAdditionalContent() {
    addLabelledComponentRow(new String[] {
        getProperties().getCloseCombatDvBonusLabel(),
        getProperties().getRangedCombatDvBonusLabel() }, new Component[] {
        initIntegerSpinner(getPageModel().getCloseCombatDvBonusModel()).getComponent(),
        initIntegerSpinner(getPageModel().getRangedCombatDvBonusModel()).getComponent() });
    addLabelledComponentRow(
        new String[] { getProperties().getMobilityPenaltyLabel(), getProperties().getFatigueLabel() },
        new Component[] {
            initIntegerSpinner(getPageModel().getMobilityPenaltyModel()).getComponent(),
            initIntegerSpinner(getPageModel().getFatigueModel()).getComponent() });
  }

  @Override
  protected boolean isTagsSupported() {
    return false;
  }
}