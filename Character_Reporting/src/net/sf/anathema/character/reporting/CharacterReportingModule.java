package net.sf.anathema.character.reporting;

import net.sf.anathema.character.generic.framework.module.CharacterModuleAdapter;

public class CharacterReportingModule extends CharacterModuleAdapter<CharacterReportingModuleObject> {

  private CharacterReportingModuleObject moduleObject;

  public void initModuleObject() {
    this.moduleObject = new CharacterReportingModuleObject();
  }

  public CharacterReportingModuleObject getModuleObject() {
    return moduleObject;
  }
}