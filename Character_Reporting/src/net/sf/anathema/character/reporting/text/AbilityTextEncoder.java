package net.sf.anathema.character.reporting.text;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.framework.reporting.pdf.PdfReportUtils;
import net.sf.anathema.lib.resources.IResources;

public class AbilityTextEncoder extends AbstractTraitTextEncoder {

  public AbilityTextEncoder(PdfReportUtils utils, IResources resources) {
    super(utils, resources);
  }

  @Override
  protected ITraitType[] getTypes(IGenericCharacter genericCharacter) {
    return AbilityType.getAbilityTypes(genericCharacter.getRules().getEdition());
  }

  @Override
  protected String getLabelKey() {
    return "TextDescription.Label.Abilities"; //$NON-NLS-1$
  }
}
