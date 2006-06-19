package net.sf.anathema.framework.reporting.jasper;

import java.io.InputStream;
import java.util.Map;

import net.sf.anathema.framework.reporting.IReport;
import net.sf.anathema.framework.reporting.IReportDataSource;
import net.sf.anathema.framework.reporting.ReportException;
import net.sf.anathema.framework.repository.IItem;

public interface IJasperReport extends IReport {

  public IReportDataSource getDataSource(IItem item);

  public Map<Object, Object> getParameters(IItem item) throws ReportException;

  public InputStream createReportInputStream();
}