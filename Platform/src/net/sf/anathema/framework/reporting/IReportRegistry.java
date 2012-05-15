package net.sf.anathema.framework.reporting;

import net.sf.anathema.framework.repository.IItem;

public interface IReportRegistry {

  void addReports(Report... report);

  Report[] getReports(IItem item);
}
