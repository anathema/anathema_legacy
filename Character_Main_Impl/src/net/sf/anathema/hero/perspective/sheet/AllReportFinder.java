package net.sf.anathema.hero.perspective.sheet;

import net.sf.anathema.framework.reporting.Report;
import net.sf.anathema.framework.repository.Item;

import java.util.List;

public interface AllReportFinder {

  List<Report> getAllReports(Item item);
}
