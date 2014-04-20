package net.sf.anathema.character.equipment.creation.view.swing;

import net.sf.anathema.character.equipment.creation.presenter.IEquipmentStatisticsCreationModel;
import net.sf.anathema.character.equipment.creation.presenter.IRangedCombatStatisticsModel;
import net.sf.anathema.character.equipment.creation.presenter.stats.properties.RangedCombatStatisticsProperties;
import net.sf.anathema.framework.environment.Resources;

import java.awt.Component;

public class RangedCombatStatisticsPresenterPage extends
    AbstractOffensiveStatisticsPresenterPage<IRangedCombatStatisticsModel, RangedCombatStatisticsProperties> {

  public RangedCombatStatisticsPresenterPage(
      Resources resources,
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

  @Override
  public boolean canFinish() {
    return false;
  }

  @Override
  protected boolean isInLegalState() {
    return super.canFinish();
  }
}