package net.sf.anathema.character.reporting.text;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.framework.reporting.ITextReportUtils;
import net.sf.anathema.lib.resources.IResources;

public class AttributeTextEncoder extends AbstractTraitTextEncoder {

  public AttributeTextEncoder(ITextReportUtils utils, IResources resources) {
    super(utils, resources);
  }

  @Override
  protected ITraitType[] getTypes(IGenericCharacter genericCharacter) {
    return AttributeType.values();
  }

  @Override
  protected String getLabelKey() {
    return "TextDescription.Label.Attributes"; //$NON-NLS-1$
  }
}