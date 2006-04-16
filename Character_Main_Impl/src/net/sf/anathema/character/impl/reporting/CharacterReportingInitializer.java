package net.sf.anathema.character.impl.reporting;

import net.sf.anathema.character.generic.framework.reporting.template.ICharacterReportTemplate;
import net.sf.anathema.framework.reporting.IReportRegistry;
import net.sf.anathema.framework.reporting.jasper.IJasperReport;
import net.sf.anathema.lib.registry.ICollectionRegistry;
import net.sf.anathema.lib.resources.IResources;

public class CharacterReportingInitializer {

  public void initReporting(
      ICollectionRegistry<ICharacterReportTemplate> reportTemplates,
      IReportRegistry reportRegistry,
      IResources resources) {
    for (ICharacterReportTemplate template : reportTemplates.getAll()) {
      String printName = resources.getString("CharacterModule.Reporting." + template.getType().getId() + ".Name"); //$NON-NLS-1$ //$NON-NLS-2$
      reportRegistry.addReport(createCharacterReport(printName, template));
    }
  }

  private IJasperReport createCharacterReport(String printName, ICharacterReportTemplate template) {
    return new CharacterReport(printName, template);
  }
}