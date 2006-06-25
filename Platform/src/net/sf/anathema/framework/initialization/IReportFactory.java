package net.sf.anathema.framework.initialization;

import net.sf.anathema.framework.extension.IExtensionPoint;
import net.sf.anathema.framework.reporting.IReport;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.IResources;

public interface IReportFactory {

  public IReport[] createReport(IResources resources, IRegistry<String, IExtensionPoint> extensionPointRegistry);
}