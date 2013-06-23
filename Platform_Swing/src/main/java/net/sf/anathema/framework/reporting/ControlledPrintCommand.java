package net.sf.anathema.framework.reporting;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.ObjectSelectionDialogPage;
import net.sf.anathema.framework.module.DefaultObjectSelectionProperties;
import net.sf.anathema.framework.repository.IObjectSelectionProperties;
import net.sf.anathema.framework.repository.Item;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.lib.gui.dialog.core.DialogResult;
import net.sf.anathema.lib.gui.dialog.userdialog.UserDialog;
import net.sf.anathema.lib.resources.Resources;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ControlledPrintCommand implements Command {

  private Resources resources;
  private IApplicationModel model;
  private JComponent parent;
  private Item item;

  public ControlledPrintCommand(Resources resources, IApplicationModel model, Item item) {
    this.resources = resources;
    this.model = model;
    this.parent = (JComponent) ((JFrame) JOptionPane.getRootFrame()).getContentPane();
    this.item = item;
  }

  @Override
  public void execute() {
    Report report = selectReport(item);
    if (report == null) {
      return;
    }
    ControlledFileChooser fileChooser = new ControlledFileChooser(item, resources, parent);
    new PrintCommand(resources, parent, item, report, fileChooser).execute();
  }

  private Report selectReport(Item item) {
    IReportRegistry reportRegistry = model.getReportRegistry();
    Report[] reports = reportRegistry.getReports(item);
    if (reports.length == 1) {
      return reports[0];
    }
    return selectReport(reports);
  }

  private Report selectReport(Report[] reports) {
    IObjectSelectionProperties properties =
            new DefaultObjectSelectionProperties(resources, "Anathema.Reporting.PrintSelection.Message", "Anathema.Reporting.PrintSelection.Title");
    ObjectSelectionDialogPage dialogPage = new ObjectSelectionDialogPage(reports, properties);
    UserDialog userDialog = new UserDialog(parent, dialogPage);
    DialogResult result = userDialog.show();
    if (result.isCanceled()) {
      return null;
    }
    return (Report) dialogPage.getSelectedObject();
  }
}
