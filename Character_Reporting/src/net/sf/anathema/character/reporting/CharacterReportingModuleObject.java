package net.sf.anathema.character.reporting;

import net.sf.anathema.character.generic.framework.module.object.ICharacterModuleObject;
import net.sf.anathema.character.reporting.extended.ExtendedEncodingRegistry;
import net.sf.anathema.character.reporting.sheet.SimpleEncodingRegistry;

public class CharacterReportingModuleObject implements ICharacterModuleObject {

  private final SimpleEncodingRegistry simpleEncodingRegistry = new SimpleEncodingRegistry();
  private final ExtendedEncodingRegistry extendedEncodingRegistry = new ExtendedEncodingRegistry();

  public SimpleEncodingRegistry getSimpleEncodingRegistry() {
    return simpleEncodingRegistry;
  }

  public ExtendedEncodingRegistry getExtendedEncodingRegistry() {
    return extendedEncodingRegistry;
  }
}
