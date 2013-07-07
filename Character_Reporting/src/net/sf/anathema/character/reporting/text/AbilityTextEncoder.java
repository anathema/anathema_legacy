package net.sf.anathema.character.reporting.text;

import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.character.main.traits.types.AbilityType;
import net.sf.anathema.framework.reporting.pdf.PdfReportUtils;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.resources.Resources;

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
