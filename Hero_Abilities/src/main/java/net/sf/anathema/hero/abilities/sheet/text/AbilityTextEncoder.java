package net.sf.anathema.hero.abilities.sheet.text;

import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.types.AbilityType;
import net.sf.anathema.framework.reporting.pdf.PdfReportUtils;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.sheet.text.AbstractTraitTextEncoder;
import net.sf.anathema.framework.environment.Resources;

public class AbilityTextEncoder extends AbstractTraitTextEncoder {

  public AbilityTextEncoder(PdfReportUtils utils, Resources resources) {
    super(utils, resources);
  }

  @Override
  protected TraitType[] getTypes(Hero hero) {
    return AbilityType.values();
  }

  @Override
  protected String getLabelKey() {
    return "TextDescription.Label.Abilities";
  }
}
