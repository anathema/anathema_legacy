package net.sf.anathema.character.reporting.sheet.second.equipment.weaponstats;

import net.sf.anathema.lib.resources.IResources;

public final class WeaponNameStatsGroup implements IWeaponStatsGroup {
  private final String title;

  public WeaponNameStatsGroup(IResources resources) {
    this.title = "Weapons";
  }

  public int getColumnCount() {
    return 1;
  }

  public String getTitle() {
    return title;
  }

  public Float[] getColumnWeights() {
    return new Float[] { new Float(6) };
  }
}