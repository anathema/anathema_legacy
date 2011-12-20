package net.sf.anathema.character.reporting.pdf.content.willpower;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.reporting.pdf.content.AbstractSubContent;
import net.sf.anathema.lib.resources.IResources;

public class WillpowerContent extends AbstractSubContent {

  private IGenericCharacter character;

  public WillpowerContent(IResources resources, IGenericCharacter character) {
    super(resources);
    this.character = character;
  }

  @Override
  public String getHeaderKey() {
    return "Willpower"; //$NON-NLS-1$
  }

  public int getWillpowerValue() {
    return  character.getTraitCollection().getTrait(OtherTraitType.Willpower).getCurrentValue();
  }

  public IExaltedEdition getEdition() {
    return character.getRules().getEdition();
  }
}
