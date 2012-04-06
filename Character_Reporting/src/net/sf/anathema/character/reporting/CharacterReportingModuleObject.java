package net.sf.anathema.character.reporting;

import net.sf.anathema.character.generic.framework.module.object.ICharacterModuleObject;
import net.sf.anathema.character.reporting.pdf.content.ReportContentRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.pages.PageRegistry;
import net.sf.anathema.initialization.Instantiater;

public class CharacterReportingModuleObject implements ICharacterModuleObject {

  private final ReportContentRegistry contentRegistry;
  private final EncoderRegistry encoderRegistry;
  private final PageRegistry additionalPageRegistry;

  public CharacterReportingModuleObject(Instantiater instantiater) {
    this.encoderRegistry = new EncoderRegistry(instantiater);
    this.contentRegistry = new ReportContentRegistry(instantiater);
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
