package net.sf.anathema.framework.reporting;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.ObjectSelectionDialogPage;
import net.sf.anathema.framework.module.DefaultObjectSelectionProperties;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.repository.IObjectSelectionProperties;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.lib.gui.dialog.core.IDialogResult;
import net.sf.anathema.lib.gui.dialog.userdialog.UserDialog;
import net.sf.anathema.lib.resources.IResources;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ControlledPrintCommand implements Command {

  private IResources resources;
  private IAnathemaModel model;
  private JComponent parent;
  private IItem item;

  public ControlledPrintCommand(IResources resources, IAnathemaModel model, IItem item) {
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
    new PrintCommand(resources, model, parent, item, report, fileChooser).execute();
  }

  private Report selectReport(IItem item) {
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
    IDialogResult result = userDialog.show();
    if (result.isCanceled()) {
      return null;
    }
    return (Report) dialogPage.getSelectedObject();
  }
}