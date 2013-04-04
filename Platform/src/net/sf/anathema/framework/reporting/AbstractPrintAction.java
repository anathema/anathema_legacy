package net.sf.anathema.framework.reporting;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.presenter.IItemManagementModelListener;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.resources.Resources;
import org.apache.commons.io.IOUtils;

import javax.swing.KeyStroke;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.awt.Desktop.isDesktopSupported;

public abstract class AbstractPrintAction extends SmartAction {
  protected final IApplicationModel anathemaModel;
  protected final Resources resources;

  public AbstractPrintAction(IApplicationModel anathemaModel, Resources resources) {
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

  public static boolean isAutoOpenSupported() {
    return isDesktopSupported();
  }
}