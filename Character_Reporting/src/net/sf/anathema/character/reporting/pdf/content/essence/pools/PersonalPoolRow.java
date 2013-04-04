package net.sf.anathema.character.reporting.pdf.content.essence.pools;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.lib.resources.Resources;

public class PersonalPoolRow extends AbstractPoolRow {

  private Resources resources;
  private IGenericCharacter character;

  public PersonalPoolRow(Resources resources, IGenericCharacter character) {
    this.resources = resources;
    this.character = character;
  }

  @Override
  public String getLabel() {
    return resources.getString("Sheet.Essence.PersonalPool"); //$NON-NLS-1$
  }

  @Override
  public int getCapacityValue() {
    return character.getPersonalPoolValue();
  }

  @Override
  public Integer getCommittedValue() {
    PeripheralPoolRow peripheralPool = new PeripheralPoolRow(resources, character);
    int committed = character.getAttunedPoolValue();
    return committed - peripheralPool.getCommittedValue();
  }
}
