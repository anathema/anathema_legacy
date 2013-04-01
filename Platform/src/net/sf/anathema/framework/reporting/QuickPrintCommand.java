package net.sf.anathema.framework.reporting;

import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.view.SwingApplicationFrame;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.lib.resources.IResources;

import javax.swing.JComponent;

public class QuickPrintCommand implements Command {

  private IResources resources;
  private DefaultReportFinder finder;
  private JComponent parent;
  private IItem item;

  public QuickPrintCommand(IResources resources, IItem item, DefaultReportFinder finder) {
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
