package net.sf.anathema.character.reporting.text;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.framework.reporting.ITextReportUtils;
import net.sf.anathema.lib.resources.IResources;

public class VirtueTextEncoder extends AbstractTraitTextEncoder<VirtueType> {

  public VirtueTextEncoder(ITextReportUtils utils, IResources resources) {
    super(utils, resources);
  }

  @Override
  protected boolean addSeparator(VirtueType type) {
    return type.ordinal() > 0;
  }

  @Override
  protected String getLabelKey() {
    return "TextDescription.Label.Virtues"; //$NON-NLS-1$
  }

  @Override
  protected VirtueType[] getTypes(IGenericCharacter genericCharacter) {
    return VirtueType.values();
  }
}