package net.sf.anathema.framework.reporting;

import net.sf.anathema.framework.repository.IItem;

public interface IReportRegistry {

  public void addReports(Report... report);

  public Report[] getReports(IItem item);
}
