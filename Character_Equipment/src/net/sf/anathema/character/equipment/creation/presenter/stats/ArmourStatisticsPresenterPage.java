package net.sf.anathema.character.equipment.creation.presenter.stats;

import java.awt.Component;

import net.sf.anathema.character.equipment.creation.model.stats.IArmourStatisticsModel;
import net.sf.anathema.character.equipment.creation.model.stats.IEquipmentStatisticsCreationModel;
import net.sf.anathema.character.equipment.creation.presenter.stats.properties.ArmourStatisticsProperties;
import net.sf.anathema.lib.resources.IResources;

public class ArmourStatisticsPresenterPage extends
    AbstractEquipmentStatisticsPresenterPage<IArmourStatisticsModel, ArmourStatisticsProperties> {

  public ArmourStatisticsPresenterPage(
      IResources resources,
      IEquipmentStatisticsCreationModel model,
      IEquipmentStatisticsCreationViewFactory viewFactory) {
    super(resources, new ArmourStatisticsProperties(resources), model, model.getArmourStatisticsModel(), viewFactory);
  }

  @Override
  protected void addAdditionalContent() {
    addLabelledComponentRow(new String[] {
        getProperties().getBashingSoakLabel(),
        getProperties().getBashingHardnessLabel() }, new Component[] {
        initIntegerSpinner(getPageModel().getBashingSoakModel()).getComponent(),
        initIntegerSpinner(getPageModel().getBashingHardnessModel()).getComponent() });
    addLabelledComponentRow(new String[] {
        getProperties().getLethalSoakLabel(),
        getProperties().getLethalHardnessLabel() }, new Component[] {
        initIntegerSpinner(getPageModel().getLethalSoakModel()).getComponent(),
        initIntegerSpinner(getPageModel().getLethalHardnessModel()).getComponent() });
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