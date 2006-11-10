package net.sf.anathema.character.reporting.text;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.framework.reporting.itext.ITextReportUtils;
import net.sf.anathema.lib.resources.IResources;

public class AttributeTextEncoder extends AbstractTraitTextEncoder<AttributeType> {

  public AttributeTextEncoder(ITextReportUtils utils, IResources resources) {
    super(utils, resources);
  }

  @Override
  protected boolean addSeparator(AttributeType type) {
    return type.ordinal() > 0;
  }

  @Override
  protected AttributeType[] getTypes(IGenericCharacter genericCharacter) {
    return AttributeType.values();
  }

  @Override
  protected String getLabelKey() {
    return "Sheet.Label.Attributes"; //$NON-NLS-1$
  }
}