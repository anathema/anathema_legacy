package net.sf.anathema.hero.framework.perspective.sheet;

import net.sf.anathema.framework.reporting.Report;
import net.sf.anathema.framework.repository.Item;

public interface DefaultReportFinder {
  Report getDefaultReport(Item item);
}