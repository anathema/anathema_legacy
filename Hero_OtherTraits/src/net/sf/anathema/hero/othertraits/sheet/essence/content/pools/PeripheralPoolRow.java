package net.sf.anathema.hero.othertraits.sheet.essence.content.pools;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.lib.resources.Resources;

public class PeripheralPoolRow extends AbstractPoolRow {

  private Resources resources;
  private IGenericCharacter character;

  public PeripheralPoolRow(Resources resources, IGenericCharacter character) {
    this.resources = resources;
    this.character = character;
  }

  @Override
  public String getLabel() {
    return resources.getString("Sheet.Essence.PeripheralPool");
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
