package net.sf.anathema.character.reporting;

import net.sf.anathema.character.reporting.pdf.content.ReportContentRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.pages.PageRegistry;
import net.sf.anathema.initialization.ObjectFactory;
import net.sf.anathema.lib.resources.Resources;

public class CharacterReportingModuleObject {

  private final ReportContentRegistry contentRegistry;
  private final EncoderRegistry encoderRegistry;
  private final PageRegistry additionalPageRegistry;

  public CharacterReportingModuleObject(ObjectFactory objectFactory, Resources resources) {
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
