package net.sf.anathema.character.reporting.text;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.framework.reporting.itext.ITextReportUtils;
import net.sf.anathema.lib.resources.IResources;

public class AbilityTextEncoder extends AbstractTraitTextEncoder<AbilityType> {

  public AbilityTextEncoder(ITextReportUtils utils, IResources resources) {
    super(utils, resources);
  }

  @Override
  protected boolean addSeparator(AbilityType type) {
    return type.ordinal() > 0;
  }

  @Override
  protected AbilityType[] getTypes(IGenericCharacter genericCharacter) {
    return AbilityType.getAbilityTypes(genericCharacter.getRules().getEdition());
  }

  @Override
  protected String getLabelKey() {
    return "TextDescription.Label.Abilities"; //$NON-NLS-1$
  }
}