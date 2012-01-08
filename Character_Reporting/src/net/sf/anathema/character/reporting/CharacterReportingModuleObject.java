package net.sf.anathema.character.reporting;

import net.sf.anathema.character.generic.framework.CharacterReflections;
import net.sf.anathema.character.generic.framework.module.object.ICharacterModuleObject;
import net.sf.anathema.character.reporting.pdf.content.ReportContentRegistry;
import net.sf.anathema.character.reporting.pdf.layout.extended.ExtendedEncodingRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.pages.PageRegistry;

public class CharacterReportingModuleObject implements ICharacterModuleObject {

  private final ExtendedEncodingRegistry extendedEncodingRegistry = new ExtendedEncodingRegistry();
  private final ReportContentRegistry contentRegistry = new ReportContentRegistry();
  private final EncoderRegistry encoderRegistry;
  private final PageRegistry additionalPageRegistry = new PageRegistry();

  public CharacterReportingModuleObject(CharacterReflections reflections) {
    this.encoderRegistry = new EncoderRegistry(reflections);
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
