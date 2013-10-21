package net.sf.anathema.hero.framework.perspective.sheet;

import net.sf.anathema.framework.reporting.Report;
import net.sf.anathema.character.main.framework.item.Item;

import java.util.List;

public interface AllReportFinder {

  List<Report> getAllReports(Item item);
}
