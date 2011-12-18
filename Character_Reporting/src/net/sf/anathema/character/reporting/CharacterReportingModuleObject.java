package net.sf.anathema.character.reporting;

import net.sf.anathema.character.generic.framework.module.object.ICharacterModuleObject;
import net.sf.anathema.character.reporting.pdf.content.ReportContentRegistry;
import net.sf.anathema.character.reporting.pdf.layout.extended.ExtendedEncodingRegistry;
import net.sf.anathema.character.reporting.pdf.layout.simple.SimpleEncodingRegistry;

public class CharacterReportingModuleObject implements ICharacterModuleObject {

  private final SimpleEncodingRegistry simpleEncodingRegistry = new SimpleEncodingRegistry();
  private final ExtendedEncodingRegistry extendedEncodingRegistry = new ExtendedEncodingRegistry();
  private final ReportContentRegistry reportContentRegistry = new ReportContentRegistry();

  public SimpleEncodingRegistry getSimpleEncodingRegistry() {
    return simpleEncodingRegistry;
  }

  public ExtendedEncodingRegistry getExtendedEncodingRegistry() {
    return extendedEncodingRegistry;
  }

  public ReportContentRegistry getReportContentRegistry() {
    return reportContentRegistry;
  }
}
