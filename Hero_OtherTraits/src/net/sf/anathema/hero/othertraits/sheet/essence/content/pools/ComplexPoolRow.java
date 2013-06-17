package net.sf.anathema.hero.othertraits.sheet.essence.content.pools;

import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.IdentifiedInteger;

public class ComplexPoolRow extends AbstractPoolRow {

  private Resources resources;
  private IdentifiedInteger complexPool;

  public ComplexPoolRow(Resources resources, IdentifiedInteger pool) {
    this.resources = resources;
    this.complexPool = pool;
  }

  @Override
  public String getLabel() {
    String poolId = complexPool.getId();
    return resources.getString("Sheet.Essence." + poolId);
  }

  @Override
  public int getCapacityValue() {
    return complexPool.getValue();
  }

  @Override
  public Integer getCommittedValue() {
    return null;
  }
}