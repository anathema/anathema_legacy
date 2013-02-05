package net.sf.anathema.framework.reporting;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.message.MessageUtilities;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.lib.gui.dialog.progress.ProgressMonitorDialog;
import net.sf.anathema.lib.io.PathUtils;
import net.sf.anathema.lib.message.Message;
import net.sf.anathema.lib.progress.INonInterruptibleRunnableWithProgress;
import net.sf.anathema.lib.progress.IProgressMonitor;
import net.sf.anathema.lib.resources.IResources;
import org.apache.commons.io.IOUtils;

import javax.swing.JComponent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.awt.Desktop.isDesktopSupported;

public class PrintCommand implements Command {

  public static final String PDF_EXTENSION = ".pdf";
  private IResources resources;
  private IAnathemaModel model;
  private JComponent parent;
  private IItem item;
  private Report report;
  private FileChooser fileChooser;

  public static boolean isAutoOpenSupported() {
    return isDesktopSupported();
  }

  public PrintCommand(IResources resources, IAnathemaModel model, JComponent parent, IItem item, Report report, FileChooser fileChooser) {
    this.resources = resources;
    this.model = model;
    this.parent = parent;
    this.item = item;
    this.report = report;
    this.fileChooser = fileChooser;
  }

  @Override
  public void execute() {
    try {
      Path selectedFile = fileChooser.getPrintFile();
      if (selectedFile == null) {
        return;
      }
      printWithProgress(selectedFile);
      openFile(selectedFile);
    } catch (IOException e) {
      handleFailedToOpenException(e);
    } catch (InvocationTargetException e) {
      handleInvocationTargetException(e);
    } catch (Exception e) {
      handleGeneralException(e);
    }
  }

  private void showMessage(Throwable e, String errorMessageKey) {
    String errorMessage = resources.getString(errorMessageKey);
    MessageUtilities.indicateMessage(getClass(), parent, new Message(errorMessage, e));
  }

  private String getErrorMessage(InvocationTargetException e) {
    if (e.getCause() instanceof FileNotFoundException) {
      return "Anathema.Reporting.Message.PrintError.FileOpen";
    }
    return "Anathema.Reporting.Message.PrintError";
  }

  private void printWithProgress(Path selectedFile) throws InvocationTargetException {
    String informativeMessage = model.generateInformativeMessage();
    new ProgressMonitorDialog(parent, informativeMessage).run(new PrintRunnable(item, report, selectedFile));
  }

  private void performPrint(IProgressMonitor monitor, IItem item, Report selectedReport, Path selectedFile) throws IOException, ReportException {
    monitor.beginTaskWithUnknownTotalWork(resources.getString("Anathema.Reporting.Print.Progress.Task"));
    OutputStream stream = null;
    try {
      stream = Files.newOutputStream(selectedFile);
      selectedReport.print(item, stream);
    } finally {
      IOUtils.closeQuietly(stream);
    }
  }

  private void openFile(Path selectedFile) throws IOException {
    if (isAutoOpenSupported()) {
      PathUtils.openOnDesktop(selectedFile);
    }
  }

  private void handleGeneralException(Exception e) {
    showMessage(e, "Anathema.Reporting.Message.PrintError");
  }

  private void handleInvocationTargetException(InvocationTargetException e) {
    showMessage(e.getCause(), getErrorMessage(e));
  }

  private void handleFailedToOpenException(IOException e) {
    showMessage(e, "Anathema.Reporting.Message.NoApplication");
  }

  private class PrintRunnable implements INonInterruptibleRunnableWithProgress {
    private final IItem item;
    private final Report selectedReport;
    private final Path selectedFile;

    public PrintRunnable(IItem item, Report selectedReport, Path selectedFile) {
      this.item = item;
      this.selectedReport = selectedReport;
      this.selectedFile = selectedFile;
    }

    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException {
      try {
        performPrint(monitor, item, selectedReport, selectedFile);
      } catch (ReportException | IOException e) {
        throw new InvocationTargetException(e);
      }
    }
  }
}
