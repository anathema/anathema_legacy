package net.sf.anathema.character.reporting;

import net.sf.anathema.character.reporting.pdf.content.ReportContentRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.pages.PageRegistry;
import net.sf.anathema.initialization.Instantiater;
import net.sf.anathema.lib.resources.IResources;

public class CharacterReportingModuleObject {

  private final ReportContentRegistry contentRegistry;
  private final EncoderRegistry encoderRegistry;
  private final PageRegistry additionalPageRegistry;

  public CharacterReportingModuleObject(Instantiater instantiater, IResources resources) {
    this.encoderRegistry = new EncoderRegistry(instantiater);
    this.contentRegistry = new ReportContentRegistry(instantiater, resources);
    this.additionalPageRegistry = new PageRegistry(instantiater);
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
