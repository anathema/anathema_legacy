package net.sf.anathema.framework.reporting;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.swing.Action;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import net.disy.commons.core.io.IOUtilities;
import net.disy.commons.core.message.Message;
import net.disy.commons.core.progress.INonInterruptableRunnableWithProgress;
import net.disy.commons.core.progress.IProgressMonitor;
import net.disy.commons.swing.action.SmartAction;
import net.disy.commons.swing.dialog.core.IDialogResult;
import net.disy.commons.swing.dialog.progress.ProgressMonitorDialog;
import net.disy.commons.swing.dialog.userdialog.UserDialog;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.ObjectSelectionDialogPage;
import net.sf.anathema.framework.message.MessageUtilities;
import net.sf.anathema.framework.module.DefaultObjectSelectionProperties;
import net.sf.anathema.framework.module.preferences.OpenPdfPreferencesElement;
import net.sf.anathema.framework.presenter.ItemManagementModelAdapter;
import net.sf.anathema.framework.presenter.resources.PlatformUI;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.repository.IObjectSelectionProperties;
import net.sf.anathema.lib.gui.file.FileChoosingUtilities;
import net.sf.anathema.lib.resources.IResources;

public class AnathemaPrintAction extends SmartAction {

  private static final long serialVersionUID = 6219757317594446843L;
  private static final String PDF_EXTENSION = ".pdf"; //$NON-NLS-1$

  private static class PrintEnabledListener extends ItemManagementModelAdapter {

    private final IReportRegistry reportRegistry;
    private final Action action;

    public PrintEnabledListener(IReportRegistry reportRegistry, Action action) {
      this.reportRegistry = reportRegistry;
      this.action = action;
    }

    @Override
    public void itemSelected(IItem item) {
      IReport[] reports = reportRegistry.getReports(item);
      action.setEnabled(reports.length > 0);
    }
  }

  private final ITextReportPrinter itextPrinter = new ITextReportPrinter();
  private final IAnathemaModel anathemaModel;
  private final IResources resources;

  public static Action createToolAction(IAnathemaModel model, IResources resources) {
    SmartAction action = new AnathemaPrintAction(model, resources);
    action.setToolTipText(resources.getString("Anathema.Reporting.Menu.PrintItem.Tooltip")); //$NON-NLS-1$
    action.setIcon(new PlatformUI(resources).getPDFTaskBarIcon());
    return action;
  }

  public static Action createMenuAction(IAnathemaModel model, IResources resources) {
    SmartAction action = new AnathemaPrintAction(model, resources);
    action.setName(resources.getString("Anathema.Reporting.Menu.PrintItem.Name") + "\u2026"); //$NON-NLS-1$ //$NON-NLS-2$
    return action;
  }

  private AnathemaPrintAction(final IAnathemaModel anathemaModel, IResources resources) {
    setAcceleratorKey(KeyStroke.getKeyStroke(KeyEvent.VK_P, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
    this.anathemaModel = anathemaModel;
    this.resources = resources;
    PrintEnabledListener listener = new PrintEnabledListener(anathemaModel.getReportRegistry(), this);
    this.anathemaModel.getItemManagement().addListener(listener);
    listener.itemSelected(anathemaModel.getItemManagement().getSelectedItem());
  }

  @Override
  protected void execute(Component parentComponent) {
    final IItem item = anathemaModel.getItemManagement().getSelectedItem();
    if (item == null) {
      return;
    }
    final IReport selectedReport = selectReport(parentComponent, item);
    if (selectedReport == null) {
      return;
    }
    String suggestedFileName = item.getDisplayName() + PDF_EXTENSION;
    final File selectedFile = FileChoosingUtilities.selectSaveFile(parentComponent, suggestedFileName);
    if (selectedFile == null) {
      return;
    }
    if (!checkFileAllowed(parentComponent, selectedFile)) {
      return;
    }
    try {
      new ProgressMonitorDialog(parentComponent, resources.getString("Anathema.Reporting.Print.Progress.Title")).run( //$NON-NLS-1$
          new INonInterruptableRunnableWithProgress() {
            public void run(IProgressMonitor monitor) throws InvocationTargetException {
              try {
                performPrint(monitor, item, selectedReport, selectedFile);
              }
              catch (ReportException e) {
                throw new InvocationTargetException(e);
              }
              catch (IOException e) {
                throw new InvocationTargetException(e);
              }
            }
          });
      if (OpenPdfPreferencesElement.openDocumentAfterPrint()) {
        Desktop.getDesktop().open(selectedFile);
      }
    }
    catch (InvocationTargetException e) {
      String errorMessage;
      if (e.getTargetException() instanceof FileNotFoundException) {
        errorMessage = resources.getString("Anathema.Reporting.Message.PrintError.FileOpen"); //$NON-NLS-1$
      }
      else {
        errorMessage = resources.getString("Anathema.Reporting.Message.PrintError"); //$NON-NLS-1$
      }
      MessageUtilities.indicateMessage(getClass(), parentComponent, new Message(errorMessage, e.getCause()));
    }
    catch (Exception e) {
      String errorMessage = resources.getString("Anathema.Reporting.Message.PrintError"); //$NON-NLS-1$
      MessageUtilities.indicateMessage(getClass(), parentComponent, new Message(errorMessage, e.getCause()));
    }
  }

  private boolean checkFileAllowed(Component parentComponent, File selectedFile) {
    String message = resources.getString("Anathema.Reporting.PrintAction.OverwriteMessage"); //$NON-NLS-1$
    String title = resources.getString("Anathema.Reporting.PrintAction.OverwriteTitle"); //$NON-NLS-1$
    return !selectedFile.exists()
        || JOptionPane.showConfirmDialog(parentComponent, message, title, JOptionPane.YES_NO_OPTION) != 1;
  }

  private void performPrint(IProgressMonitor monitor, IItem item, IReport selectedReport, File selectedFile)
      throws IOException,
      ReportException {
    monitor.beginTask(resources.getString("Anathema.Reporting.Print.Progress.Task"), IProgressMonitor.UNKNOWN); //$NON-NLS-1$
    FileOutputStream stream = null;
    try {
      stream = new FileOutputStream(selectedFile);
      itextPrinter.printReport(item, (IITextReport) selectedReport, stream);
    }
    finally {
      IOUtilities.close(stream);
    }
  }

  private IReport selectReport(Component parentComponent, IItem item) {
    IReportRegistry reportRegistry = anathemaModel.getReportRegistry();
    IReport[] reports = reportRegistry.getReports(item);
    if (reports.length == 1) {
      return reports[0];
    }
    return selectReport(parentComponent, reports);
  }

  private IReport selectReport(Component parentComponent, IReport[] reports) {
    IObjectSelectionProperties properties = new DefaultObjectSelectionProperties(
        resources,
        "Anathema.Reporting.PrintSelection.Message", "Anathema.Reporting.PrintSelection.Title"); //$NON-NLS-1$ //$NON-NLS-2$
    ObjectSelectionDialogPage dialogPage = new ObjectSelectionDialogPage(reports, properties);
    UserDialog userDialog = new UserDialog(parentComponent, dialogPage);
    IDialogResult result = userDialog.show();
    if (result.isCanceled()) {
      return null;
    }
    return (IReport) dialogPage.getSelectedObject();
  }
}
