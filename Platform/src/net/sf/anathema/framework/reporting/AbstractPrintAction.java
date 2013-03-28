package net.sf.anathema.framework.reporting;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.message.MessageUtilities;
import net.sf.anathema.framework.presenter.IItemManagementModelListener;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.io.PathUtils;
import net.sf.anathema.lib.lang.StringUtilities;
import net.sf.anathema.lib.message.Message;
import net.sf.anathema.lib.resources.IResources;
import org.apache.commons.io.IOUtils;

import javax.swing.KeyStroke;
import java.awt.Component;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.awt.Desktop.isDesktopSupported;

public abstract class AbstractPrintAction extends SmartAction {
  public static final String PDF_EXTENSION = ".pdf"; //$NON-NLS-1$
  protected final IApplicationModel anathemaModel;
  protected final IResources resources;

  public AbstractPrintAction(IApplicationModel anathemaModel, IResources resources) {
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

  protected void performPrint(IItem item, Report selectedReport,
                            Path selectedFile) throws IOException, ReportException {
    OutputStream stream = null;
    try {
      stream = Files.newOutputStream(selectedFile);
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

  protected void handleFailedToOpenException(Component parentComponent, IOException e) {
    showMessage(parentComponent, e, "Anathema.Reporting.Message.NoApplication");
  }

  protected void handleAlreadyOpenException(Component parentComponent, FileNotFoundException e) {
    showMessage(parentComponent, e, "Anathema.Reporting.Message.PrintError.FileOpen");
  }

  private void showMessage(Component parentComponent, Throwable e, String errorMessageKey) {
    String errorMessage = resources.getString(errorMessageKey); //$NON-NLS-1$
    MessageUtilities.indicateMessage(getClass(), parentComponent, new Message(errorMessage, e));
  }

  protected void openFile(Path selectedFile) throws IOException {
    if (isAutoOpenSupported()) {
      PathUtils.openOnDesktop(selectedFile);
    }
  }

  public static boolean isAutoOpenSupported() {
    return isDesktopSupported();
  }

  protected String getBaseName(IItem item) {
    return StringUtilities.getFileNameRepresentation(item.getDisplayName());
  }
}