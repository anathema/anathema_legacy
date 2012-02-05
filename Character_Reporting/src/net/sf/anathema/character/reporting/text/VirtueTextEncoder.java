package net.sf.anathema.character.reporting.text;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.framework.reporting.pdf.PdfReportUtils;
import net.sf.anathema.lib.resources.IResources;

public class VirtueTextEncoder extends AbstractTraitTextEncoder {

  public VirtueTextEncoder(PdfReportUtils utils, IResources resources) {
    super(utils, resources);
  }

  @Override
  protected String getLabelKey() {
    return "TextDescription.Label.Virtues"; //$NON-NLS-1$
  }

  @Override
  protected ITraitType[] getTypes(IGenericCharacter genericCharacter) {
    return VirtueType.values();
  }
}
