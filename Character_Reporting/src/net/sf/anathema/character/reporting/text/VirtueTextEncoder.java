package net.sf.anathema.character.reporting.text;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.framework.reporting.pdf.PdfReportUtils;
import net.sf.anathema.lib.resources.Resources;

public class VirtueTextEncoder extends AbstractTraitTextEncoder {

  public VirtueTextEncoder(PdfReportUtils utils, Resources resources) {
    super(utils, resources);
  }

  @Override
  protected String getLabelKey() {
    return "TextDescription.Label.Virtues";
  }

  @Override
  protected TraitType[] getTypes(IGenericCharacter genericCharacter) {
    return VirtueType.values();
  }
}
