package net.sf.anathema.framework.reporting;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.view.SwingApplicationFrame;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.lib.resources.IResources;

import javax.swing.JComponent;

public class QuickPrintCommand implements Command {

  private IResources resources;
  private IAnathemaModel model;
  private JComponent parent;
  private IItem item;

  public QuickPrintCommand(IResources resources, IAnathemaModel model, IItem item) {
    this.resources = resources;
    this.model = model;
    this.parent = SwingApplicationFrame.getParentComponent();
    this.item = item;
  }

  @Override
  public void execute() {
    Report report = new DefaultReportFinder(model, resources).getDefaultReport(item);
    if (report == null) {
      return;
    }
    QuickFileChooser fileChooser = new QuickFileChooser(item, resources);
    new PrintCommand(resources, model, parent, item, report, fileChooser).execute();
  }
}
