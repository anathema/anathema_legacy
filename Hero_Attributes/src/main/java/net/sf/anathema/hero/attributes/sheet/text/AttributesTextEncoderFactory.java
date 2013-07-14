package net.sf.anathema.hero.attributes.sheet.text;

import net.sf.anathema.framework.reporting.pdf.PdfReportUtils;
import net.sf.anathema.hero.sheet.text.HeroTextEncoder;
import net.sf.anathema.hero.sheet.text.HeroTextEncoderFactory;
import net.sf.anathema.hero.sheet.text.RegisteredTextEncoderFactory;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.resources.Resources;

@RegisteredTextEncoderFactory
@Weight(weight = 300)
public class AttributesTextEncoderFactory implements HeroTextEncoderFactory {

  @Override
  public HeroTextEncoder create(PdfReportUtils utils, Resources resources) {
    return new AttributeTextEncoder(utils, resources);
  }
}
