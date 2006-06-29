package net.sf.anathema.character.impl.module.reporting;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.reporting.template.ICharacterReportTemplate;
import net.sf.anathema.character.impl.reporting.CharacterReport;
import net.sf.anathema.framework.extension.IAnathemaExtension;
import net.sf.anathema.framework.reporting.IReport;
import net.sf.anathema.lib.registry.ICollectionRegistry;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.IResources;

public class JasperReportFactory extends AbstractCharacterReportFactory {

  public IReport[] createReport(IResources resources, IRegistry<String, IAnathemaExtension> extensionPointRegistry) {
    ICharacterGenerics characterGenerics = getCharacterGenerics(extensionPointRegistry);
    ICollectionRegistry<ICharacterReportTemplate> reportTemplates = characterGenerics.getReportTemplateRegistry();
    List<IReport> jasperReports = new ArrayList<IReport>();
    for (ICharacterReportTemplate template : reportTemplates.getAll()) {
      String printName = resources.getString("CharacterModule.Reporting." + template.getType().getId() + ".Name"); //$NON-NLS-1$ //$NON-NLS-2$
      jasperReports.add(new CharacterReport(printName, template));
    }
    return jasperReports.toArray(new IReport[jasperReports.size()]);
  }
}