package net.sf.anathema.framework.reporting;

import java.io.InputStream;
import java.util.Map;

import net.sf.anathema.framework.repository.IItem;

public interface IReport {

  public IReportDataSource getDataSource(IItem item);

  public Map getParameters(IItem item) throws ReportException;

  public boolean supports(IItem item);

  public InputStream createReportInputStream();
}