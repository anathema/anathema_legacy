package net.sf.anathema.character.reporting;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.module.CharacterModuleAdapter;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.reporting.sheet.SecondEditionEncodingRegistry;
import net.sf.anathema.character.reporting.sheet.common.PdfBackgroundEncoder;
import net.sf.anathema.character.reporting.sheet.common.PdfHorizontalLineContentEncoder;
import net.sf.anathema.lib.resources.IResources;

public class CharacterReportingModule extends CharacterModuleAdapter<CharacterReportingModuleObject> {

  private CharacterReportingModuleObject moduleObject;

  public void initModuleObject() {
    this.moduleObject = new CharacterReportingModuleObject();
  }

  @Override
  public void addReportTemplates(ICharacterGenerics generics, IResources resources) {
    SecondEditionEncodingRegistry registry = moduleObject.getSecondEditionEncodingRegistry();
    registry.setGreatCurseEncoder(CharacterType.MORTAL, new PdfHorizontalLineContentEncoder(2, "Languages"));
    registry.setAnimaEncoder(CharacterType.MORTAL, new PdfBackgroundEncoder(resources, registry.getBaseFont()));
  }

  public CharacterReportingModuleObject getModuleObject() {
    return moduleObject;
  }
}