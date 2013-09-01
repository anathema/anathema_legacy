package net.sf.anathema.hero.spiritual.sheet.virtues.text;

import net.sf.anathema.framework.reporting.pdf.PdfReportUtils;
import net.sf.anathema.hero.sheet.text.HeroTextEncoder;
import net.sf.anathema.hero.sheet.text.HeroTextEncoderFactory;
import net.sf.anathema.hero.sheet.text.RegisteredTextEncoderFactory;
import net.sf.anathema.framework.environment.dependencies.Weight;
import net.sf.anathema.framework.environment.Resources;

@RegisteredTextEncoderFactory
@Weight(weight = 400)
public class VirtuesTextEncoderFactory implements HeroTextEncoderFactory {

  @Override
  public HeroTextEncoder create(PdfReportUtils utils, Resources resources) {
    return new VirtueTextEncoder(utils, resources);
  }
}
