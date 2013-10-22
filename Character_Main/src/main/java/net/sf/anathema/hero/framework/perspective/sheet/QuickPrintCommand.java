package net.sf.anathema.hero.framework.perspective.sheet;

import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.reporting.Report;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.interaction.Command;

public class QuickPrintCommand implements Command {

  private final Environment environment;
  private final DefaultReportFinder finder;
  private final Hero hero;

  public QuickPrintCommand(Environment environment, DefaultReportFinder finder, Hero hero) {
    this.environment = environment;
    this.finder = finder;
    this.hero = hero;
  }

  @Override
  public void execute() {
    Report report = finder.getDefaultReport(hero);
    if (report == null) {
      return;
    }
    QuickFileChooser fileChooser = new QuickFileChooser(environment, hero);
    new PrintCommand(environment, report, fileChooser, hero).execute();
  }
}