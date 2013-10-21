package net.sf.anathema.hero.framework.perspective.sheet;

import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.reporting.Report;
import net.sf.anathema.hero.model.Hero;

import static net.sf.anathema.hero.framework.perspective.sheet.PrintCommand.PDF_EXTENSION;

public class ControlledPrintWithSelectedReport {
  private final Environment environment;
  private final Hero hero;
  private final Report report;

  public ControlledPrintWithSelectedReport(Environment environment, Report report, Hero hero) {
    this.environment = environment;
    this.report = report;
    this.hero = hero;
  }

  public void execute() {
    FileChooserConfiguration configuration = new FileChooserConfiguration("PDF Files", "*" + PDF_EXTENSION);
    ControlledFileChooser fileChooser = new ControlledFileChooser(environment, configuration);
    new PrintCommand(environment, report, fileChooser, hero).execute();
  }
}