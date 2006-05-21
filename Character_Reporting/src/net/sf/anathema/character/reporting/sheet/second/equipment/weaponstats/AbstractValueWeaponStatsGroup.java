package net.sf.anathema.character.reporting.sheet.second.equipment.weaponstats;

import net.sf.anathema.character.reporting.sheet.second.equipment.WeaponEncodingUtilities;

public abstract class AbstractValueWeaponStatsGroup implements IWeaponStatsGroup {

  public final Float[] getColumnWeights() {
    return WeaponEncodingUtilities.createStandardColumnWeights(getColumnCount());
  }
}