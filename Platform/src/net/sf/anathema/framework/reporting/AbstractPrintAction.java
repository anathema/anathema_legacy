package net.sf.anathema.framework.reporting;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.message.MessageUtilities;
import net.sf.anathema.framework.presenter.IItemManagementModelListener;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.gui.dialog.progress.ProgressMonitorDialog;
import net.sf.anathema.lib.message.Message;
import net.sf.anathema.lib.progress.INonInterruptibleRunnableWithProgress;
import net.sf.anathema.lib.progress.IProgressMonitor;
import net.sf.anathema.lib.resources.IResources;
import org.apache.commons.io.IOUtils;

import javax.swing.KeyStroke;
import java.awt.Component;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import static java.awt.Desktop.isDesktopSupported;
import static net.sf.anathema.lib.progress.IProgressMonitor.UNKNOWN;

public abstract class AbstractPrintAction extends SmartAction {
  public static final String PDF_EXTENSION = ".pdf"; //$NON-NLS-1$
  protected final IAnathemaModel anathemaModel;
  protected final IResources resources;

  public AbstractPrintAction(IAnathemaModel anathemaModel, IResources resources) {
    this.anathemaModel = anathemaModel;
    this.resources = resources;
    setHotKey();
    startEnablingListener();
  }

  private void startEnablingListener() {
    IItemManagementModelListener listener = createEnablingListener();
    anathemaModel.getItemManagement().addListener(listener);
    listener.itemSelected(anathemaModel.getItemManagement().getSelectedItem());
  }

  private void setHotKey() {
    setAcceleratorKey(createKeyStroke());
  }

  protected void printWithProgress(Component parentComponent, final IItem item, final Report selectedReport,
                                   final File selectedFile) throws InvocationTargetException {
    new ProgressMonitorDialog(parentComponent, resources.getString("Anathema.Reporting.Print.Progress.Title")).run(
            //$NON-NLS-1$
            new INonInterruptibleRunnableWithProgress() {
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
  }

  private void performPrint(IProgressMonitor monitor, IItem item, Report selectedReport,
                            File selectedFile) throws IOException, ReportException {
    monitor.beginTaskWithUnknownTotalWork(resources.getString("Anathema.Reporting.Print.Progress.Task")); //$NON-NLS-1$
    FileOutputStream stream = null;
    try {
      stream = new FileOutputStream(selectedFile);
      selectedReport.print(item, stream);
    } finally {
      IOUtils.closeQuietly(stream);
    }
  }

  protected abstract KeyStroke createKeyStroke();

  protected abstract IItemManagementModelListener createEnablingListener();

  protected void handleGeneralException(Component parentComponent, Exception e) {
    showMessage(parentComponent, e, "Anathema.Reporting.Message.PrintError");
  }

  protected void handleInvocationTargetException(Component parentComponent, InvocationTargetException e) {
    showMessage(parentComponent, e.getCause(), getErrorMessage(e));
  }

  protected void handleFailedToOpenException(Component parentComponent, IOException e) {
    showMessage(parentComponent, e, "Anathema.Reporting.Message.NoApplication");
  }

  private void showMessage(Component parentComponent, Throwable e, String errorMessageKey) {
    String errorMessage = resources.getString(errorMessageKey); //$NON-NLS-1$
    MessageUtilities.indicateMessage(getClass(), parentComponent, new Message(errorMessage, e));
  }

  private String getErrorMessage(InvocationTargetException e) {
    if (e.getCause() instanceof FileNotFoundException) {
      return "Anathema.Reporting.Message.PrintError.FileOpen"; //$NON-NLS-1$
    }
    return "Anathema.Reporting.Message.PrintError"; //$NON-NLS-1$
  }

  protected void openFile(File selectedFile) throws IOException {
    if (isAutoOpenSupported()) {
      Desktop.getDesktop().open(selectedFile);
    }
  }

  public static boolean isAutoOpenSupported() {
    return isDesktopSupported();
  }
}