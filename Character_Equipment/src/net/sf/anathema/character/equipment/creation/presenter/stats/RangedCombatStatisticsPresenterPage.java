package net.sf.anathema.character.equipment.creation.presenter.stats;

import java.awt.Component;

import net.sf.anathema.character.equipment.creation.model.stats.IEquipmentStatisticsCreationModel;
import net.sf.anathema.character.equipment.creation.model.stats.IRangedCombatStatisticsModel;
import net.sf.anathema.character.equipment.creation.properties.RangedCombatStatisticsProperties;
import net.sf.anathema.lib.resources.IResources;

public class RangedCombatStatisticsPresenterPage extends
    AbstractOffensiveStatisticsPresenterPage<IRangedCombatStatisticsModel, RangedCombatStatisticsProperties> {

  public RangedCombatStatisticsPresenterPage(
      IResources resources,
      IEquipmentStatisticsCreationModel model,
      IEquipmentStatisticsCreationViewFactory viewFactory) {
    super(
        resources,
        new RangedCombatStatisticsProperties(resources),
        model,
        model.getRangedWeaponStatisticsModel(),
        viewFactory);
  }

  @Override
  protected void addIndividualRows() {
    initSpeedAndRangeRow();
  }

  private void initSpeedAndRangeRow() {
    String[] labels = new String[] { getProperties().getSpeedLabel(), getProperties().getRangeLabel() };
    addLabelledComponentRow(labels, new Component[] {
        initIntegerSpinner(getPageModel().getSpeedModel()).getComponent(),
        initIntegerSpinner(getPageModel().getRangeModel()).getComponent() });
  }
}