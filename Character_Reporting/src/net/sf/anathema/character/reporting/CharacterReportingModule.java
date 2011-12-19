package net.sf.anathema.character.reporting;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.module.CharacterModuleAdapter;
import net.sf.anathema.character.reporting.pdf.content.ReportContentRegistry;
import net.sf.anathema.character.reporting.pdf.content.essence.ExtendedEssenceContent;
import net.sf.anathema.character.reporting.pdf.content.essence.ExtendedEssenceContentFactory;
import net.sf.anathema.character.reporting.pdf.content.essence.SimpleEssenceContent;
import net.sf.anathema.character.reporting.pdf.content.essence.SimpleEssenceContentFactory;
import net.sf.anathema.character.reporting.pdf.content.virtues.VirtueContent;
import net.sf.anathema.character.reporting.pdf.content.virtues.VirtueContentFactory;
import net.sf.anathema.lib.resources.IResources;

public class CharacterReportingModule extends CharacterModuleAdapter<CharacterReportingModuleObject> {

  private CharacterReportingModuleObject moduleObject;

  public void initModuleObject() {
    this.moduleObject = new CharacterReportingModuleObject();
  }

  public CharacterReportingModuleObject getModuleObject() {
    return moduleObject;
  }

  public void addReportTemplates(ICharacterGenerics generics, IResources resources) {
    ReportContentRegistry registry = moduleObject.getReportContentRegistry();
    registry.addFactory(VirtueContent.class, new VirtueContentFactory(resources));
    registry.addFactory(ExtendedEssenceContent.class, new ExtendedEssenceContentFactory(resources));
    registry.addFactory(SimpleEssenceContent.class, new SimpleEssenceContentFactory(resources));
  }
}
