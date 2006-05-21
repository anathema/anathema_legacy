package net.sf.anathema.character.reporting.sheet.second.equipment.weaponstats;

import net.sf.anathema.lib.resources.IResources;

public class SpeedWeaopnStatsGroup extends AbstractValueWeaponStatsGroup {

  private String title;

  public SpeedWeaopnStatsGroup(IResources resources) {
    this.title = "Speed";
  }

  public int getColumnCount() {
    return 1;
  }

  public String getTitle() {
    return title;
  }
}