package net.sf.anathema.hero.perspective.sheet;

import net.sf.anathema.framework.ObjectSelectionDialogPage;
import net.sf.anathema.framework.module.DefaultObjectSelectionProperties;
import net.sf.anathema.framework.reporting.Report;
import net.sf.anathema.framework.repository.IObjectSelectionProperties;
import net.sf.anathema.framework.repository.Item;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.lib.gui.dialog.core.DialogResult;
import net.sf.anathema.lib.gui.dialog.userdialog.UserDialog;
import net.sf.anathema.lib.resources.Resources;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.util.List;

public class ControlledPrintCommand implements Command {

  private final Resources resources;
  private final JComponent parent;
  private final Item item;
  private final AllReportFinder reportFinder;

  public ControlledPrintCommand(Resources resources, Item item, AllReportFinder finder) {
    this.resources = resources;
    this.parent = (JComponent) ((JFrame) JOptionPane.getRootFrame()).getContentPane();
    this.item = item;
    this.reportFinder = finder;
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
    List<Report> reports = reportFinder.getAllReports(item);
    if (reports.size() == 1) {
      return reports.get(0);
    }
    return selectReport(reports);
  }

  private Report selectReport(List<Report> reports) {
    IObjectSelectionProperties properties =
            new DefaultObjectSelectionProperties(resources, "Anathema.Reporting.PrintSelection.Message", "Anathema.Reporting.PrintSelection.Title");
    Report[] reportArray = reports.toArray(new Report[reports.size()]);
    ObjectSelectionDialogPage dialogPage = new ObjectSelectionDialogPage(reportArray, properties);
    UserDialog userDialog = new UserDialog(parent, dialogPage);
    DialogResult result = userDialog.show();
    if (result.isCanceled()) {
      return null;
    }
    return (Report) dialogPage.getSelectedObject();
  }
}
