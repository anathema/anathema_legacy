package net.sf.anathema.framework.reporting;

import net.disy.commons.core.io.IOUtilities;
import net.disy.commons.core.message.Message;
import net.disy.commons.core.progress.INonInterruptableRunnableWithProgress;
import net.disy.commons.core.progress.IProgressMonitor;
import net.disy.commons.swing.action.SmartAction;
import net.disy.commons.swing.dialog.progress.ProgressMonitorDialog;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.message.MessageUtilities;
import net.sf.anathema.framework.presenter.ItemManagementModelAdapter;
import net.sf.anathema.framework.presenter.resources.PlatformUI;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.resources.IResources;

import javax.swing.Action;
import javax.swing.KeyStroke;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class AnathemaQuickPrintAction extends SmartAction {

  private static final long serialVersionUID = 6219757317594446843L;
  private static final String PDF_EXTENSION = ".pdf"; //$NON-NLS-1$

  private class PrintEnabledListener extends ItemManagementModelAdapter {

    private final Action action;

    public PrintEnabledListener(Action action) {
      this.action = action;
    }

    @Override
    public void itemSelected(IItem item) {
      Report defaultReport = getDefaultReport(item);
      boolean quickPrintPossible = defaultReport != null;
      action.setEnabled(quickPrintPossible);
    }
  }

  private final IAnathemaModel anathemaModel;
  private final IResources resources;

  public static Action createToolAction(IAnathemaModel model, IResources resources) {
    SmartAction action = new AnathemaQuickPrintAction(model, resources);
    action.setToolTipText(resources.getString("Anathema.Reporting.Menu.QuickPrint.Tooltip")); //$NON-NLS-1$
    action.setIcon(new PlatformUI(resources).getPDFTaskBarIcon());
    return action;
  }

  public static Action createMenuAction(IAnathemaModel model, IResources resources) {
    SmartAction action = new AnathemaQuickPrintAction(model, resources);
    action.setName(resources.getString("Anathema.Reporting.Menu.QuickPrint.Name") + "\u2026"); //$NON-NLS-1$ //$NON-NLS-2$
    return action;
  }

  private AnathemaQuickPrintAction(final IAnathemaModel anathemaModel, IResources resources) {
    setAcceleratorKey(KeyStroke.getKeyStroke(KeyEvent.VK_P, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
    this.anathemaModel = anathemaModel;
    this.resources = resources;
    PrintEnabledListener listener = new PrintEnabledListener(this);
    this.anathemaModel.getItemManagement().addListener(listener);
    listener.itemSelected(anathemaModel.getItemManagement().getSelectedItem());
  }

  private Report getDefaultReport(IItem item) {
    String reportName = resources.getString("CharacterModule.Reporting.Sheet.Name");
    for (Report report : anathemaModel.getReportRegistry().getReports(item)) {
      if (reportName.equals(report.toString())) {
        return report;
      }
    }
    return null;
  }

  private File getPrintFile(IItem item) throws IOException {
    File tempFile = File.createTempFile(item.getDisplayName(), PDF_EXTENSION);
    tempFile.deleteOnExit();
    return tempFile;
  }

  @Override
  protected void execute(Component parentComponent) {
    final IItem item = anathemaModel.getItemManagement().getSelectedItem();
    if (item == null) {
      return;
    }
    final Report selectedReport = getDefaultReport(item);
    if (selectedReport == null) {
      return;
    }
    try {
      final File selectedFile = getPrintFile(item);
      new ProgressMonitorDialog(parentComponent, resources.getString("Anathema.Reporting.Print.Progress.Title")).run( //$NON-NLS-1$
              new INonInterruptableRunnableWithProgress() {
                @Override
                public void run(IProgressMonitor monitor) throws InvocationTargetException {
                  try {
                    performPrint(monitor, item, selectedReport, selectedFile);
                  } catch (ReportException e) {
                    throw new InvocationTargetException(e);
                  } catch (IOException e) {
                    throw new InvocationTargetException(e);
                  }
                }
              });
      Desktop.getDesktop().open(selectedFile);
    } catch (InvocationTargetException e) {
      String errorMessage;
      if (e.getTargetException() instanceof FileNotFoundException) {
        errorMessage = resources.getString("Anathema.Reporting.Message.PrintError.FileOpen"); //$NON-NLS-1$
      } else {
        errorMessage = resources.getString("Anathema.Reporting.Message.PrintError"); //$NON-NLS-1$
      }
      MessageUtilities.indicateMessage(getClass(), parentComponent, new Message(errorMessage, e.getCause()));
    } catch (Exception e) {
      String errorMessage = resources.getString("Anathema.Reporting.Message.PrintError"); //$NON-NLS-1$
      MessageUtilities.indicateMessage(getClass(), parentComponent, new Message(errorMessage, e.getCause()));
    }
  }

  private void performPrint(IProgressMonitor monitor, IItem item, Report selectedReport, File selectedFile)
          throws IOException,
          ReportException {
    monitor.beginTask(resources.getString("Anathema.Reporting.Print.Progress.Task"), IProgressMonitor.UNKNOWN); //$NON-NLS-1$
    FileOutputStream stream = null;
    try {
      stream = new FileOutputStream(selectedFile);
      selectedReport.print(item, stream);
    } finally {
      IOUtilities.close(stream);
    }
  }
}
