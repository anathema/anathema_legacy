package net.sf.anathema.development.reporting.generation;

import java.io.File;

import net.sf.anathema.character.generic.framework.reporting.template.ICharacterReportTemplate;
import net.sf.anathema.character.generic.framework.reporting.template.voidstate.ExaltVoidstateReportTemplate;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.impl.reporting.CharacterReport;
import net.sf.anathema.framework.reporting.IJasperReport;
import net.sf.anathema.lib.resources.IResources;

public class VoidStateSolarGenerationData extends AbstractSolarGenerationData {

  private final IResources resources;

  public VoidStateSolarGenerationData(IResources resources) {
    this.resources = resources;
  }

  public IJasperReport createReport() {
    ICharacterReportTemplate template = new ExaltVoidstateReportTemplate(CharacterType.SOLAR, resources);
    return new CharacterReport("Voidstate Solar Character Sheet", template );
  }

  public File createFile() {
    return new File("Voidstate Solar.pdf");
  }
}