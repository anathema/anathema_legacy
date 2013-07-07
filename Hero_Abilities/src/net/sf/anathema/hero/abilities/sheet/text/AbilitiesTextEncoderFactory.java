package net.sf.anathema.hero.abilities.sheet.text;

import net.sf.anathema.hero.sheet.text.HeroTextEncoder;
import net.sf.anathema.hero.sheet.text.HeroTextEncoderFactory;
import net.sf.anathema.hero.sheet.text.RegisteredTextEncoderFactory;
import net.sf.anathema.framework.reporting.pdf.PdfReportUtils;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.resources.Resources;

@RegisteredTextEncoderFactory
@Weight(weight = 500)
public class AbilitiesTextEncoderFactory implements HeroTextEncoderFactory {

  @Override
  public HeroTextEncoder create(PdfReportUtils utils, Resources resources) {
    return new AbilityTextEncoder(utils, resources);
  }
}
