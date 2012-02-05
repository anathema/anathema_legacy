package net.sf.anathema.character.reporting;

import net.sf.anathema.character.generic.framework.module.object.ICharacterModuleObject;
import net.sf.anathema.character.reporting.pdf.content.ReportContentRegistry;
import net.sf.anathema.character.reporting.pdf.layout.extended.ExtendedEncodingRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.pages.PageRegistry;
import net.sf.anathema.initialization.Instantiater;

public class CharacterReportingModuleObject implements ICharacterModuleObject {

  private final ExtendedEncodingRegistry extendedEncodingRegistry;
  private final ReportContentRegistry contentRegistry;
  private final EncoderRegistry encoderRegistry;
  private final PageRegistry additionalPageRegistry;

  public CharacterReportingModuleObject(Instantiater instantiater) {
    this.encoderRegistry = new EncoderRegistry(instantiater);
    this.extendedEncodingRegistry = new ExtendedEncodingRegistry(instantiater);
    this.contentRegistry = new ReportContentRegistry(instantiater);
    this.additionalPageRegistry = new PageRegistry(instantiater);
  }

  public ExtendedEncodingRegistry getExtendedEncodingRegistry() {
    return extendedEncodingRegistry;
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