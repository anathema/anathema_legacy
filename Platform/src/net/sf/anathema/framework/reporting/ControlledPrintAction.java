package net.sf.anathema.framework.reporting;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.ObjectSelectionDialogPage;
import net.sf.anathema.framework.module.DefaultObjectSelectionProperties;
import net.sf.anathema.framework.presenter.IItemManagementModelListener;
import net.sf.anathema.framework.presenter.resources.PlatformUI;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.repository.IObjectSelectionProperties;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.gui.dialog.core.IDialogResult;
import net.sf.anathema.lib.gui.dialog.userdialog.UserDialog;
import net.sf.anathema.lib.gui.file.FileChoosingUtilities;
import net.sf.anathema.lib.resources.IResources;

import javax.swing.Action;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import java.awt.Component;
import java.awt.Event;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;

import static javax.swing.KeyStroke.getKeyStroke;
import static net.sf.anathema.framework.module.preferences.OpenPdfPreferencesElement.openDocumentAfterPrint;

public class ControlledPrintAction extends AbstractPrintAction {

  public static Action createMenuAction(IAnathemaModel model, IResources resources) {
    SmartAction action = new ControlledPrintAction(model, resources);
    action.setName(
            resources.getString("Anathema.Reporting.Menu.PrintItem.Name") + "\u2026"); //$NON-NLS-1$ //$NON-NLS-2$
    return action;
  }

  public static Action createToolAction(IAnathemaModel model, IResources resources) {
    SmartAction action = new ControlledPrintAction(model, resources);
    action.setToolTipText(resources.getString("Anathema.Reporting.Menu.PrintItem.Name")); //$NON-NLS-1$
    action.setIcon(new PlatformUI(resources).getPDFTaskBarIcon());
    return action;
  }

  private ControlledPrintAction(IAnathemaModel anathemaModel, IResources resources) {
    super(anathemaModel, resources);
  }

  @Override
  protected KeyStroke createKeyStroke() {
    return getKeyStroke(KeyEvent.VK_P, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask() | Event.SHIFT_MASK);
  }

  @Override
  protected IItemManagementModelListener createEnablingListener() {
    return new PrintEnabledListener(this, anathemaModel.getReportRegistry());
  }

  @Override
  protected void execute(Component parentComponent) {
    IItem item = anathemaModel.getItemManagement().getSelectedItem();
    if (item == null) {
      return;
    }
    Report selectedReport = selectReport(parentComponent, item);
    if (selectedReport == null) {
      return;
    }
    String suggestedFileName = getBaseName(item) + PDF_EXTENSION;
    Path selectedFile = FileChoosingUtilities.selectSaveFile(parentComponent, suggestedFileName);
    if (selectedFile == null) {
      return;
    }
    if (!checkFileAllowed(parentComponent, selectedFile)) {
      return;
    }
    try {
      printWithProgress(parentComponent, item, selectedReport, selectedFile);
      if (openDocumentAfterPrint()) {
        openFile(selectedFile);
      }
    } catch (IOException e) {
      handleFailedToOpenException(parentComponent, e);
    } catch (InvocationTargetException e) {
      handleInvocationTargetException(parentComponent, e);
    } catch (Exception e) {
      handleGeneralException(parentComponent, e);
    }
  }

  private boolean checkFileAllowed(Component parentComponent, Path selectedFile) {
    String message = resources.getString("Anathema.Reporting.PrintAction.OverwriteMessage"); //$NON-NLS-1$
    String title = resources.getString("Anathema.Reporting.PrintAction.OverwriteTitle"); //$NON-NLS-1$
    return !Files.exists(selectedFile) || JOptionPane.showConfirmDialog(parentComponent, message, title,
            JOptionPane.YES_NO_OPTION) != 1;
  }

  private Report selectReport(Component parentComponent, IItem item) {
    IReportRegistry reportRegistry = anathemaModel.getReportRegistry();
    Report[] reports = reportRegistry.getReports(item);
    if (reports.length == 1) {
      return reports[0];
    }
    return selectReport(parentComponent, reports);
  }

  private Report selectReport(Component parentComponent, Report[] reports) {
    IObjectSelectionProperties properties = new DefaultObjectSelectionProperties(resources,
            "Anathema.Reporting.PrintSelection.Message",
            "Anathema.Reporting.PrintSelection.Title"); //$NON-NLS-1$ //$NON-NLS-2$
    ObjectSelectionDialogPage dialogPage = new ObjectSelectionDialogPage(reports, properties);
    UserDialog userDialog = new UserDialog(parentComponent, dialogPage);
    IDialogResult result = userDialog.show();
    if (result.isCanceled()) {
      return null;
    }
    return (Report) dialogPage.getSelectedObject();
  }
}