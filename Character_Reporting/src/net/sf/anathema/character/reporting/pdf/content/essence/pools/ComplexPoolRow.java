package net.sf.anathema.character.reporting.pdf.content.essence.pools;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IdentifiedInteger;

public class ComplexPoolRow extends AbstractPoolRow {

  private IResources resources;
  private IdentifiedInteger complexPool;

  public ComplexPoolRow(IResources resources, IdentifiedInteger pool) {
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