package net.sf.anathema.character.reporting;

import net.sf.anathema.character.generic.framework.module.object.ICharacterModuleObject;
import net.sf.anathema.character.reporting.sheet.SecondEditionEncodingRegistry;

public class CharacterReportingModuleObject implements ICharacterModuleObject {

  private final SecondEditionEncodingRegistry secondEditionEncodingRegistry = new SecondEditionEncodingRegistry();

  public SecondEditionEncodingRegistry getSecondEditionEncodingRegistry() {
    return secondEditionEncodingRegistry;
  }
}