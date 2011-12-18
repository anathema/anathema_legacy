package net.sf.anathema.character.reporting.pdf.content.essence.recovery;

import net.sf.anathema.character.reporting.pdf.content.essence.pools.AbstractRecoveryRow;
import net.sf.anathema.lib.resources.IResources;

public class SpecialRecoveryRow extends AbstractRecoveryRow {

  private IResources resources;
  private String labelKey;

  public SpecialRecoveryRow(IResources resources, String labelKey) {
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
