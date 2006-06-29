package net.sf.anathema.character.impl.module.reporting;

import net.sf.anathema.character.impl.reporting.TextReport;
import net.sf.anathema.framework.extension.IAnathemaExtension;
import net.sf.anathema.framework.initialization.IReportFactory;
import net.sf.anathema.framework.reporting.IReport;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.IResources;

public class TextReportFactory implements IReportFactory {

  public IReport[] createReport(IResources resources, IRegistry<String, IAnathemaExtension> extensionPointRegistry) {
    return new IReport[] { new TextReport() };
  }
}