package net.sf.anathema.character.reporting.pdf.content.essence;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.reporting.pdf.content.AbstractSubBoxContent;
import net.sf.anathema.lib.resources.Resources;

public class SimpleEssenceContent extends AbstractSubBoxContent {

  private IGenericCharacter character;

  public SimpleEssenceContent(Resources resources, IGenericCharacter character) {
    super(resources);
    this.character = character;
  }

  @Override
  public String getHeaderKey() {
    return "Essence";
  }

  @Override
  public boolean hasContent() {
    return true;
  }

  public int getEssenceValue() {
    return character.getTraitCollection().getTrait(OtherTraitType.Essence).getCurrentValue();
  }

  public int getEssenceMax() {
    return character.getEssenceLimitation().getAbsoluteLimit(character);
  }

  public int getNumberOfPoolLines() {
    return (hasPersonalPool() ? 1 : 0) + (hasPeripheralPool() ? 1 : 0);
  }

  public boolean hasPeripheralPool() {
    return getPeripheralPool() != null;
  }

  public boolean hasPersonalPool() {
    return getPersonalPool() != null;
  }

  public String getPersonalPool() {
    return character.getPersonalPool();
  }

  public String getPeripheralPool() {
    return character.getPeripheralPool();
  }

  public String getPersonalPoolLabel() {
    return getResources().getString("Sheet.Essence.PersonalPool");
  }

  public String getPeripheralPoolLabel() {
    return getResources().getString("Sheet.Essence.PeripheralPool");
  }

  public String getAvailableText() {
    return " " + getResources().getString("Sheet.Essence.Available");
  }

  public String getTotalString(String poolValue) {
    return  poolValue + " " + getResources().getString("Sheet.Essence.Total") + " / ";
  }
}
