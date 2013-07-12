package net.sf.anathema.hero.framework.perspective.sheet;

import net.sf.anathema.framework.reporting.Report;
import net.sf.anathema.framework.repository.Item;
import net.sf.anathema.framework.view.SwingApplicationFrame;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.lib.resources.Resources;

import javax.swing.JComponent;

public class QuickPrintCommand implements Command {

  private Resources resources;
  private DefaultReportFinder finder;
  private JComponent parent;
  private Item item;

  public QuickPrintCommand(Resources resources, Item item, DefaultReportFinder finder) {
    this.resources = resources;
    this.finder = finder;
    this.parent = SwingApplicationFrame.getParentComponent();
    this.item = item;
  }

  @Override
  public void execute() {
    Report report = finder.getDefaultReport(item);
    if (report == null) {
      return;
    }
    QuickFileChooser fileChooser = new QuickFileChooser(item, resources);
    new PrintCommand(resources, parent, item, report, fileChooser).execute();
  }
}
