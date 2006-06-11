package net.sf.anathema.character.reporting;

import net.sf.anathema.character.generic.framework.module.object.ICharacterModuleObject;
import net.sf.anathema.character.reporting.sheet.PdfEncodingRegistry;

public class CharacterReportingModuleObject implements ICharacterModuleObject {

  private final PdfEncodingRegistry secondEditionEncodingRegistry = new PdfEncodingRegistry();

  public PdfEncodingRegistry getPdfEncodingRegistry() {
    return secondEditionEncodingRegistry;
  }
}