package net.sf.anathema.hero.spiritual.sheet.essence.content.recovery;

import net.sf.anathema.hero.spiritual.sheet.essence.content.pools.AbstractRecoveryRow;
import net.sf.anathema.framework.environment.Resources;

public class NaturalRecoveryRow extends AbstractRecoveryRow {

  private Resources resources;

  public NaturalRecoveryRow(Resources resources) {
    this.resources = resources;
  }

  @Override
  public String getLabel() {
    return resources.getString("Sheet.Essence.NaturalRecovery");
  }

  @Override
  public Integer getAtEase() {
    return 4;
  }

  @Override
  public Integer getRelaxed() {
    return 8;
  }

  @Override
  public boolean isMarked() {
    return false;
  }
}
