package net.sf.anathema.character.equipment.creation.view.swing;

import net.sf.anathema.character.equipment.creation.presenter.IEquipmentStatisticsCreationModel;
import net.sf.anathema.character.equipment.creation.presenter.stats.properties.CloseCombatStatisticsProperties;
import net.sf.anathema.framework.environment.Resources;

public class CloseCombatStatisticsPresenterPage extends WeaponPresenterPage {
  public CloseCombatStatisticsPresenterPage(Resources resources, IEquipmentStatisticsCreationModel overallModel, IWeaponStatisticsView view) {
    super(new CloseCombatStatisticsProperties(resources), overallModel, overallModel.getCloseCombatStatsticsModel(), view);
  }
}
