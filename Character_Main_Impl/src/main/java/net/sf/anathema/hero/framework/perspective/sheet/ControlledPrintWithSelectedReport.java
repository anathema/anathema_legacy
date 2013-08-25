package net.sf.anathema.hero.framework.perspective.sheet;

import net.sf.anathema.framework.reporting.Report;
import net.sf.anathema.framework.repository.Item;
import net.sf.anathema.lib.resources.Resources;

public class ControlledPrintWithSelectedReport {
  private final Item item;
  private final Resources resources;
  private Report report;

  public ControlledPrintWithSelectedReport(Item item, Resources resources, Report report) {
    this.item = item;
    this.resources = resources;
    this.report = report;
  }

  public void execute() {
    ControlledFileChooser fileChooser = new ControlledFileChooser(resources);
    new PrintCommand(resources, item, report, fileChooser).execute();
  }
}