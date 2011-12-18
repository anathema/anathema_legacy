package net.sf.anathema.character.reporting.pdf.content.essence.pools;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.lib.resources.IResources;

public class PeripheralPoolRow extends AbstractPoolRow {

  private IResources resources;
  private IGenericCharacter character;

  public PeripheralPoolRow(IResources resources, IGenericCharacter character) {
    this.resources = resources;
    this.character = character;
  }

  @Override
  public String getLabel() {
    return resources.getString("Sheet.Essence.PeripheralPool"); //$NON-NLS-1$
  }

  @Override
  public int getCapacityValue() {
    return character.getPeripheralPoolValue();
  }

  @Override
  public Integer getCommittedValue() {
    int committed = character.getAttunedPoolValue();
    return Math.min(character.getPeripheralPoolValue(), committed);
  }
}
