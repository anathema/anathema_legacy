package net.sf.anathema.framework.reporting;

import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.swing.MessageUtilities;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.lib.io.PathUtils;
import net.sf.anathema.lib.message.Message;
import net.sf.anathema.lib.resources.Resources;
import org.apache.commons.io.IOUtils;

import javax.swing.JComponent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.awt.Desktop.isDesktopSupported;

public class PrintCommand implements Command {

  public static final String PDF_EXTENSION = ".pdf";
  private final Resources resources;
  private final JComponent parent;
  private final IItem item;
  private final Report report;
  private final FileChooser fileChooser;

  public static boolean isAutoOpenSupported() {
    return isDesktopSupported();
  }

  public PrintCommand(Resources resources, JComponent parent, IItem item, Report report, FileChooser fileChooser) {
    this.resources = resources;
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
      performPrint(item, report, selectedFile);
      openFile(selectedFile);
    }catch (FileNotFoundException e) {
      handleAlreadyOpenException(e);
    } catch (IOException e) {
      handleFailedToOpenException(e);
    } catch (Exception e) {
      handleGeneralException(e);
    }
  }

  private void showMessage(Throwable e, String errorMessageKey) {
    String errorMessage = resources.getString(errorMessageKey);
    MessageUtilities.indicateMessage(getClass(), parent, new Message(errorMessage, e));
  }

  private void performPrint(IItem item, Report selectedReport, Path selectedFile) throws IOException, ReportException {
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

  private void handleAlreadyOpenException(FileNotFoundException e) {
    showMessage(e, "Anathema.Reporting.Message.PrintError.FileOpen");
  }

  private void handleGeneralException(Exception e) {
    showMessage(e, "Anathema.Reporting.Message.PrintError");
  }

  private void handleFailedToOpenException(IOException e) {
    showMessage(e, "Anathema.Reporting.Message.NoApplication");
  }
}