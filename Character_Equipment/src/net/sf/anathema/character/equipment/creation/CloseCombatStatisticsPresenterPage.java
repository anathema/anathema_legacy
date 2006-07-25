package net.sf.anathema.character.equipment.creation;

import java.awt.Component;

import net.sf.anathema.character.equipment.creation.model.ICloseCombatStatsticsModel;
import net.sf.anathema.character.equipment.creation.model.IEquipmentStatisticsCreationModel;
import net.sf.anathema.character.equipment.creation.properties.CloseCombatStatisticsProperties;
import net.sf.anathema.lib.resources.IResources;

public class CloseCombatStatisticsPresenterPage extends
    AbstractOffensiveStatisticsPresenterPage<ICloseCombatStatsticsModel, CloseCombatStatisticsProperties> {

  public CloseCombatStatisticsPresenterPage(
      IResources resources,
      IEquipmentStatisticsCreationModel model,
      IEquipmentStatisticsCreationViewFactory viewFactory) {
    super(
        resources,
        new CloseCombatStatisticsProperties(resources),
        model,
        model.getCloseCombatStatsticsModel(),
        viewFactory);
  }

  @Override
  protected void addIndividualRows() {
    initSpeedAndDefenseRow();
  }

  private void initSpeedAndDefenseRow() {
    String[] labels = new String[] { getProperties().getSpeedLabel(), getProperties().getDefenseLabel() };
    addLabelledComponentRow(labels, new Component[] {
        initIntegerSpinner(getPageModel().getSpeedModel()).getComponent(),
        initIntegerSpinner(getPageModel().getDefenseModel()).getComponent() });
  }
}