package net.sf.anathema.framework.initialization;

import net.sf.anathema.framework.extension.IAnathemaExtension;
import net.sf.anathema.framework.reporting.Report;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.IResources;

public interface IReportFactory {

  Report[] createReport(IResources resources, IRegistry<String, IAnathemaExtension> extensionPointRegistry);
}
