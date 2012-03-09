package net.sf.anathema.framework.reporting;

import net.disy.commons.swing.action.SmartAction;
import net.disy.commons.swing.dialog.core.IDialogResult;
import net.disy.commons.swing.dialog.userdialog.UserDialog;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.ObjectSelectionDialogPage;
import net.sf.anathema.framework.module.DefaultObjectSelectionProperties;
import net.sf.anathema.framework.module.preferences.OpenPdfPreferencesElement;
import net.sf.anathema.framework.presenter.IItemManagementModelListener;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.repository.IObjectSelectionProperties;
import net.sf.anathema.lib.gui.file.FileChoosingUtilities;
import net.sf.anathema.lib.resources.IResources;

import javax.swing.Action;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Event;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.File;
import java.lang.reflect.InvocationTargetException;

import static javax.swing.KeyStroke.getKeyStroke;

public class AnathemaPrintAction extends AbstractPrintAction {

  public static Action createMenuAction(IAnathemaModel model, IResources resources) {
    SmartAction action = new AnathemaPrintAction(model, resources);
    action.setName(
            resources.getString("Anathema.Reporting.Menu.PrintItem.Name") + "\u2026"); //$NON-NLS-1$ //$NON-NLS-2$
    return action;
  }

  private AnathemaPrintAction(final IAnathemaModel anathemaModel, IResources resources) {
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
    String suggestedFileName = item.getDisplayName() + PDF_EXTENSION;
    File selectedFile = FileChoosingUtilities.selectSaveFile(parentComponent, suggestedFileName);
    if (selectedFile == null) {
      return;
    }
    if (!checkFileAllowed(parentComponent, selectedFile)) {
      return;
    }
    try {
      printWithProgress(parentComponent, item, selectedReport, selectedFile);
      if (OpenPdfPreferencesElement.openDocumentAfterPrint()) {
        Desktop.getDesktop().open(selectedFile);
      }
    } catch (InvocationTargetException e) {
      handleInvocationTargetException(parentComponent, e);
    } catch (Exception e) {
      handleGeneralException(parentComponent, e);
    }
  }

  private boolean checkFileAllowed(Component parentComponent, File selectedFile) {
    String message = resources.getString("Anathema.Reporting.PrintAction.OverwriteMessage"); //$NON-NLS-1$
    String title = resources.getString("Anathema.Reporting.PrintAction.OverwriteTitle"); //$NON-NLS-1$
    return !selectedFile.exists() || JOptionPane.showConfirmDialog(parentComponent, message, title,
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