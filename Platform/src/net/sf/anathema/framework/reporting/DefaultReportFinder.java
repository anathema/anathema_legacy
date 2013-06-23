package net.sf.anathema.framework.reporting;

import net.sf.anathema.framework.repository.Item;

public interface DefaultReportFinder {
  Report getDefaultReport(Item item);
}