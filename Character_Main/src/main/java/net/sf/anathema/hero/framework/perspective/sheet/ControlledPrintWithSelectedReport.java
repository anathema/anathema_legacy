package net.sf.anathema.hero.framework.perspective.sheet;

import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.reporting.Report;
import net.sf.anathema.character.main.framework.item.Item;

import static net.sf.anathema.hero.framework.perspective.sheet.PrintCommand.PDF_EXTENSION;

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
    FileChooserConfiguration configuration = new FileChooserConfiguration("PDF Files", "*" + PDF_EXTENSION);
    ControlledFileChooser fileChooser = new ControlledFileChooser(environment, configuration);
    new PrintCommand(environment, item, report, fileChooser).execute();
  }
}