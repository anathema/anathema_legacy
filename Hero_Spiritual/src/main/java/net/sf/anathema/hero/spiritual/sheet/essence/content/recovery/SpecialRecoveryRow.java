package net.sf.anathema.hero.spiritual.sheet.essence.content.recovery;

import net.sf.anathema.hero.spiritual.sheet.essence.content.pools.AbstractRecoveryRow;
import net.sf.anathema.lib.resources.Resources;

public class SpecialRecoveryRow extends AbstractRecoveryRow {

  private Resources resources;
  private String labelKey;

  public SpecialRecoveryRow(Resources resources, String labelKey) {
    this.resources = resources;
    this.labelKey = labelKey;
  }
  
  @Override
  public String getLabel() {
    return resources.getString(labelKey);
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
    return false;
  }
}
