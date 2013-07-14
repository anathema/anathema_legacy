package net.sf.anathema.hero.spiritual.sheet.virtues.text;

import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.character.main.traits.types.VirtueType;
import net.sf.anathema.framework.reporting.pdf.PdfReportUtils;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.sheet.text.AbstractTraitTextEncoder;
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
  protected TraitType[] getTypes(Hero hero) {
    return VirtueType.values();
  }
}
