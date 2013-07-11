package net.sf.anathema.hero.othertraits.sheet.essence.content.recovery;

import net.sf.anathema.hero.othertraits.sheet.essence.content.pools.AbstractRecoveryRow;
import net.sf.anathema.lib.resources.Resources;

public class TotalRecoveryRow extends AbstractRecoveryRow {

  private Resources resources;

  public TotalRecoveryRow(Resources resources) {
    this.resources = resources;
  }

  @Override
  public String getLabel() {
    return resources.getString("Sheet.Essence.TotalPerHour");
  }

  @Override
  public Integer getAtEase() {
    return null;
  }

  @Override
  public Integer getRelaxed() {
    return null;
  }

  @Override
  public boolean isMarked() {
    return true;
  }
}
