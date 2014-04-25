package net.sf.anathema.character.equipment.creation.view.swing;

import net.sf.anathema.character.equipment.creation.presenter.stats.properties.RangedCombatStatisticsProperties;
import net.sf.anathema.framework.environment.Resources;

public class RangedCombatStatisticsPresenterPage extends EquipmentStatisticsPresenterPage<RangedCombatStatisticsProperties> implements WeaponStatsView {

  public RangedCombatStatisticsPresenterPage(Resources resources) {
    super(new RangedCombatStatisticsProperties(resources));
  }
}