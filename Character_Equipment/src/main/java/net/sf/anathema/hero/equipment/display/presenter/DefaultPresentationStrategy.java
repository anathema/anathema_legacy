package net.sf.anathema.hero.equipment.display.presenter;

import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IEquipmentStats;

public class DefaultPresentationStrategy implements StatsPresentationStrategy {
  @SuppressWarnings("UnusedParameters")
  public DefaultPresentationStrategy(IEquipmentStats equipment) {
    //nothing to do
  }

  @Override
  public boolean shouldStatsBeShown() {
    return true;
  }
}
