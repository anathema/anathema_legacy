package net.sf.anathema.character.equipment.creation.view.swing;

import net.sf.anathema.character.equipment.creation.presenter.ICloseCombatStatsticsModel;
import net.sf.anathema.character.equipment.creation.presenter.IEquipmentStatisticsCreationModel;
import net.sf.anathema.character.equipment.creation.presenter.stats.properties.CloseCombatStatisticsProperties;
import net.sf.anathema.framework.environment.Resources;

import java.awt.Component;

public class CloseCombatStatisticsPresenterPage extends
    AbstractOffensiveStatisticsPresenterPage<ICloseCombatStatsticsModel, CloseCombatStatisticsProperties> {

  public CloseCombatStatisticsPresenterPage(
          Resources resources,
          IEquipmentStatisticsCreationModel model) {
    super(
        resources,
        new CloseCombatStatisticsProperties(resources),
        model,
        model.getCloseCombatStatsticsModel()
    );
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