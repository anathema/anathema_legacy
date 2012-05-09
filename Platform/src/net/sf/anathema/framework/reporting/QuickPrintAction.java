package net.sf.anathema.framework.reporting;

import net.disy.commons.swing.action.SmartAction;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.presenter.IItemManagementModelListener;
import net.sf.anathema.framework.presenter.resources.PlatformUI;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.resources.IResources;

import javax.swing.Action;
import javax.swing.KeyStroke;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import static javax.swing.KeyStroke.getKeyStroke;

public class QuickPrintAction extends AbstractPrintAction {

  public static Action createToolAction(IAnathemaModel model, IResources resources) {
    SmartAction action = new QuickPrintAction(model, resources);
    action.setToolTipText(resources.getString("Anathema.Reporting.Menu.QuickPrint.Tooltip")); //$NON-NLS-1$
    action.setIcon(new PlatformUI(resources).getPDFTaskBarIcon());
    return action;
  }

  public static Action createMenuAction(IAnathemaModel model, IResources resources) {
    SmartAction action = new QuickPrintAction(model, resources);
    action.setName(
            resources.getString("Anathema.Reporting.Menu.QuickPrint.Name") + "\u2026"); //$NON-NLS-1$ //$NON-NLS-2$
    return action;
  }

  private QuickPrintAction(IAnathemaModel anathemaModel, IResources resources) {
    super(anathemaModel, resources);
  }

  @Override
  protected KeyStroke createKeyStroke() {
    return getKeyStroke(KeyEvent.VK_P, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask());
  }

  @Override
  protected IItemManagementModelListener createEnablingListener() {
    return new QuickPrintEnabledListener(this, new DefaultReportFinder(anathemaModel, resources));
  }

  private File getPrintFile(IItem item) throws IOException {
    File tempFile = File.createTempFile(item.getDisplayName(), PDF_EXTENSION);
    tempFile.deleteOnExit();
    return tempFile;
  }

  @Override
  protected void execute(Component parentComponent) {
    IItem item = anathemaModel.getItemManagement().getSelectedItem();
    if (item == null) {
      return;
    }
    Report selectedReport = new DefaultReportFinder(anathemaModel, resources).getDefaultReport(item);
    if (selectedReport == null) {
      return;
    }
    try {
      File selectedFile = getPrintFile(item);
      printWithProgress(parentComponent, item, selectedReport, selectedFile);
      openFile(selectedFile);
    } catch (IOException e) {
      handleFailedToOpenException(parentComponent, e);
    } catch (InvocationTargetException e) {
      handleInvocationTargetException(parentComponent, e);
    } catch (Exception e) {
      handleGeneralException(parentComponent, e);
    }
  }
}