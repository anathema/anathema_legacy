package net.sf.anathema.hero.concept.sheet.text.concept;

import net.sf.anathema.framework.reporting.pdf.PdfReportUtils;
import net.sf.anathema.hero.sheet.text.HeroTextEncoder;
import net.sf.anathema.hero.sheet.text.HeroTextEncoderFactory;
import net.sf.anathema.hero.sheet.text.RegisteredTextEncoderFactory;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.framework.environment.Resources;

@RegisteredTextEncoderFactory
@Weight(weight = 200)
public class ConceptTextEncoderFactory implements HeroTextEncoderFactory {

  @Override
  public HeroTextEncoder create(PdfReportUtils utils, Resources resources) {
    return new ConceptTextEncoder(utils, resources);
  }
}
