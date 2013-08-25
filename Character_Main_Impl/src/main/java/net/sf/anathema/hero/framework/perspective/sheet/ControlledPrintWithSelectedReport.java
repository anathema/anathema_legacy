package net.sf.anathema.hero.framework.perspective.sheet;

import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.reporting.Report;
import net.sf.anathema.framework.repository.Item;

public class ControlledPrintWithSelectedReport {
  private final Item item;
  private final Environment environment;
  private Report report;

  public ControlledPrintWithSelectedReport(Item item, Environment environment, Report report) {
    this.item = item;
    this.environment = environment;
    this.report = report;
  }

  public void execute() {
    ControlledFileChooser fileChooser = new ControlledFileChooser();
    new PrintCommand(environment, item, report, fileChooser).execute();
  }
}