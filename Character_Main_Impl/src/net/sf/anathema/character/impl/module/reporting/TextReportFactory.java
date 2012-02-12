package net.sf.anathema.character.impl.module.reporting;

import net.sf.anathema.character.impl.reporting.TextReport;
import net.sf.anathema.framework.extension.IAnathemaExtension;
import net.sf.anathema.framework.initialization.IReportFactory;
import net.sf.anathema.framework.reporting.Report;
import net.sf.anathema.initialization.ReportFactory;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.IResources;

@ReportFactory
public class TextReportFactory implements IReportFactory {

  public Report[] createReport(IResources resources, IRegistry<String, IAnathemaExtension> extensionPointRegistry) {
    return new Report[]{new TextReport(resources)};
  }
}
