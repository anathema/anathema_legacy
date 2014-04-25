package net.sf.anathema.character.equipment.creation.view.swing;

import net.sf.anathema.character.equipment.creation.presenter.stats.properties.CloseCombatStatisticsProperties;
import net.sf.anathema.framework.environment.Resources;

public class CloseCombatStatisticsPresenterPage extends EquipmentStatisticsPresenterPage<CloseCombatStatisticsProperties> implements EquipmentStatsView {
  public CloseCombatStatisticsPresenterPage(Resources resources) {
    super(new CloseCombatStatisticsProperties(resources));
  }
}
