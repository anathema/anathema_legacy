package net.sf.anathema.character.reporting.pdf.content.essence.pools;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.lib.resources.Resources;

public class OverdrivePoolRow extends AbstractPoolRow {

  private Resources resources;
  private IGenericCharacter character;

  public OverdrivePoolRow(Resources resources, IGenericCharacter character) {
    this.resources = resources;
    this.character = character;
  }

  @Override
  public String getLabel() {
    return resources.getString("Sheet.Essence.OverdrivePool");
  }

  @Override
  public int getCapacityValue() {
    return character.getOverdrivePoolValue();
  }

  @Override
  public Integer getCommittedValue() {
   return null;
  }
}
