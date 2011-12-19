package net.sf.anathema.character.reporting.pdf.content.essence;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.reporting.pdf.content.AbstractSubContent;
import net.sf.anathema.lib.resources.IResources;

public class SimpleEssenceContent extends AbstractSubContent {

  private IGenericCharacter character;

  public SimpleEssenceContent(IResources resources, IGenericCharacter character) {
    super(resources);
    this.character = character;
  }

  @Override
  public String getHeaderKey() {
    return "Essence"; //$NON-NLS-1$
  }

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
    return getResources().getString("Sheet.Essence.PersonalPool"); //$NON-NLS-1$
  }

  public String getPeripheralPoolLabel() {
    return getResources().getString("Sheet.Essence.PeripheralPool"); //$NON-NLS-1$
  }

  public String getAvailableText() {
    return " " + getResources().getString("Sheet.Essence.Available"); //$NON-NLS-1$ //$NON-NLS-2$
  }

  public String getTotalString(String poolValue) {
    return  poolValue + " " + getResources().getString("Sheet.Essence.Total") + " / "; //$NON-NLS-1$ //$NON-NLS-2$
  }
}
