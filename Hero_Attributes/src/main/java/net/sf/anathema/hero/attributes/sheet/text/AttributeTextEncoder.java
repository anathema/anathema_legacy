package net.sf.anathema.hero.attributes.sheet.text;

import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.character.main.traits.types.AttributeType;
import net.sf.anathema.framework.reporting.pdf.PdfReportUtils;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.sheet.text.AbstractTraitTextEncoder;
import net.sf.anathema.lib.resources.Resources;

public class AttributeTextEncoder extends AbstractTraitTextEncoder {

  public AttributeTextEncoder(PdfReportUtils utils, Resources resources) {
    super(utils, resources);
  }

  @Override
  protected TraitType[] getTypes(Hero hero) {
    return AttributeType.values();
  }

  @Override
  protected String getLabelKey() {
    return "TextDescription.Label.Attributes";
  }
}
