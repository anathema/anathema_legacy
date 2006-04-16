package net.sf.anathema.framework.reporting;

import net.sf.anathema.framework.repository.IItem;

public interface IReportRegistry {

  public void addReport(IReport reporter);

  public IReport[] getReports(IItem item);
}