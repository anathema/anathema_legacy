package net.sf.anathema.character.equipment.creation;

import java.awt.Component;

import net.sf.anathema.character.equipment.creation.model.IArmourStatisticsModel;
import net.sf.anathema.character.equipment.creation.model.IEquipmentStatisticsCreationModel;
import net.sf.anathema.character.equipment.creation.properties.ArmourStatisticsProperties;
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
        initIntegerSpinner(getPageModel().getBashingSoakModel(), 1).getComponent(),
        initIntegerSpinner(getPageModel().getBashingHardnessModel(), 1).getComponent() });
    addLabelledComponentRow(new String[] {
        getProperties().getLethalSoakLabel(),
        getProperties().getLethalHardnessLabel() }, new Component[] {
        initIntegerSpinner(getPageModel().getLethalSoakModel(), 1).getComponent(),
        initIntegerSpinner(getPageModel().getLethalHardnessModel(), 1).getComponent() });
    addLabelledComponentRow(new String[] {
        getProperties().getMobilityPenaltyLabel(),
        getProperties().getFatigueLabel() }, new Component[] {
        initIntegerSpinner(getPageModel().getMobilityPenaltyModel(), 1).getComponent(),
        initIntegerSpinner(getPageModel().getFatigueModel(), 1).getComponent() });
  }

  @Override
  protected boolean isTagsSupported() {
    return true;
  }
}