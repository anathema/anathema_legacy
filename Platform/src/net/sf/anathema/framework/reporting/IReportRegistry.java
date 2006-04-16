package net.sf.anathema.framework.reporting;

import net.sf.anathema.framework.repository.IItem;

public interface IReportRegistry {

  public void addReport(IJasperReport reporter);

  public IJasperReport[] getReports(IItem item);

}
