package net.sf.anathema.character.reporting.pdf.content.essence.recovery;

import net.sf.anathema.character.reporting.pdf.content.essence.pools.AbstractRecoveryRow;
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
