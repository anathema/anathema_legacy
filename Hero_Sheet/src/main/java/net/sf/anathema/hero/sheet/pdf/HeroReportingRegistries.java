package net.sf.anathema.hero.sheet.pdf;

import net.sf.anathema.hero.sheet.pdf.content.ReportContentRegistry;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.EncoderRegistry;
import net.sf.anathema.hero.sheet.pdf.page.PageRegistry;
import net.sf.anathema.framework.environment.ObjectFactory;
import net.sf.anathema.framework.environment.Resources;

public class HeroReportingRegistries {

  private final ReportContentRegistry contentRegistry;
  private final EncoderRegistry encoderRegistry;
  private final PageRegistry additionalPageRegistry;

  public HeroReportingRegistries(ObjectFactory objectFactory, Resources resources) {
    this.encoderRegistry = new EncoderRegistry(objectFactory);
    this.contentRegistry = new ReportContentRegistry(objectFactory, resources);
    this.additionalPageRegistry = new PageRegistry(objectFactory);
  }

  public ReportContentRegistry getContentRegistry() {
    return contentRegistry;
  }

  public EncoderRegistry getEncoderRegistry() {
    return encoderRegistry;
  }

  public PageRegistry getAdditionalPageRegistry() {
    return additionalPageRegistry;
  }
}
