package net.sf.anathema.character.reporting;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.module.CharacterModuleAdapter;
import net.sf.anathema.character.reporting.sheet.PdfEncodingRegistry;
import net.sf.anathema.lib.resources.IResources;

public class CharacterReportingModule extends CharacterModuleAdapter<CharacterReportingModuleObject> {

  private CharacterReportingModuleObject moduleObject;

  public void initModuleObject() {
    this.moduleObject = new CharacterReportingModuleObject();
  }

  @Override
  public void addReportTemplates(ICharacterGenerics generics, IResources resources) {
    PdfEncodingRegistry registry = moduleObject.getPdfEncodingRegistry();
  }

  public CharacterReportingModuleObject getModuleObject() {
    return moduleObject;
  }
}