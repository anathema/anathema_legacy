package net.sf.anathema.character.reporting.pdf.content.essence.recovery;

import net.sf.anathema.character.reporting.pdf.content.essence.pools.AbstractRecoveryRow;
import net.sf.anathema.lib.resources.IResources;

public class NaturalRecoveryRow extends AbstractRecoveryRow {

  private IResources resources;

  public NaturalRecoveryRow(IResources resources) {
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
