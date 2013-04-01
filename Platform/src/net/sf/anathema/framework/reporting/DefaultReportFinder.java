package net.sf.anathema.framework.reporting;

import net.sf.anathema.framework.repository.IItem;

public interface DefaultReportFinder {
  Report getDefaultReport(IItem item);
}