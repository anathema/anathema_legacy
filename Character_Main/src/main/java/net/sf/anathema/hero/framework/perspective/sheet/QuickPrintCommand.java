package net.sf.anathema.hero.framework.perspective.sheet;

import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.reporting.Report;
import net.sf.anathema.character.main.framework.item.Item;
import net.sf.anathema.interaction.Command;

public class QuickPrintCommand implements Command {

  private final Environment environment;
  private final DefaultReportFinder finder;
  private final Item item;

  public QuickPrintCommand(Environment environment, Item item, DefaultReportFinder finder) {
    this.environment = environment;
    this.finder = finder;
    this.item = item;
  }

  @Override
  public void execute() {
    Report report = finder.getDefaultReport(item);
    if (report == null) {
      return;
    }
    QuickFileChooser fileChooser = new QuickFileChooser(item, environment);
    new PrintCommand(environment, item, report, fileChooser).execute();
  }
}