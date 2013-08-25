package net.sf.anathema.hero.framework.perspective.sheet;

import net.sf.anathema.framework.reporting.Report;
import net.sf.anathema.framework.repository.Item;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.lib.resources.Resources;

public class QuickPrintCommand implements Command {

  private Resources resources;
  private DefaultReportFinder finder;
  private Item item;

  public QuickPrintCommand(Resources resources, Item item, DefaultReportFinder finder) {
    this.resources = resources;
    this.finder = finder;
    this.item = item;
  }

  @Override
  public void execute() {
    Report report = finder.getDefaultReport(item);
    if (report == null) {
      return;
    }
    QuickFileChooser fileChooser = new QuickFileChooser(item, resources);
    new PrintCommand(resources, item, report, fileChooser).execute();
  }
}