package net.sf.anathema.framework.reporting;

import net.sf.anathema.framework.repository.Item;

public interface IReportRegistry {

  void addReports(Report... report);

  Report[] getReports(Item item);
}
