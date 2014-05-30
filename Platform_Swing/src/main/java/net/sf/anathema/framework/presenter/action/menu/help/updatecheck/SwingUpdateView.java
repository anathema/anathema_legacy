package net.sf.anathema.framework.presenter.action.menu.help.updatecheck;

import de.idos.updates.Version;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.gui.dialog.userdialog.DefaultDialogConfiguration;
import net.sf.anathema.lib.gui.dialog.userdialog.UserDialog;

import javax.swing.SwingUtilities;

public class SwingUpdateView implements UpdateView {

  private final UpdateDialogPage page;
  private final UserDialog dialog;

  public SwingUpdateView(String title, String currentVersionLabel, String latestVersionLabel) {
    this.page = new UpdateDialogPage(title, currentVersionLabel, latestVersionLabel);
    DefaultDialogConfiguration dialogConfiguration = DefaultDialogConfiguration.createWithOkOnly(page);
    this.dialog = new UserDialog(null, dialogConfiguration);
    dialog.getDialog().setModal(false);
  }

  @Override
  public void show() {
    dialog.show();
  }

  @Override
  public void showDescription(String message) {
    page.showDescription(message);
  }

  @Override
  public void showLatestVersion(String versionNumber) {
    page.showLatestVersion(versionNumber);
  }

  @Override
  public void showInstalledVersion(Version installedVersion) {
    page.showInstalledVersion(installedVersion);
  }

  @Override
  public void showChangelog(String text) {
    page.showChangelog(text);
  }

  @Override
  public void enableUpdate() {
    page.enableUpdate();
  }

  @Override
  public void disableUpdate() {
    page.disableUpdate();
  }

  @Override
  public void showFilesToDownload(int numberOfElements) {
    page.showFilesToDownload(numberOfElements);
  }

  @Override
  public void showExpectedFileSize(int intSize) {
    page.showExpectedFileSize(intSize);
  }

  @Override
  public void showProgressOnFile(int progress) {
    page.showProgressOnFile(progress);
  }

  @Override
  public void showFilesAlreadyLoaded(int elementsHandled) {
    page.showFilesAlreadyLoaded(elementsHandled);
  }

  @Override
  public void showProgressMessage(String string) {
    page.showProgressMessage(string);
  }

  @Override
  public Tool addTool() {
    return page.addTool();
  }

  @Override
  public void refresh() {
    SwingUtilities.invokeLater(() -> {
      dialog.updateDescription();
      dialog.getDialogControl().checkInputValid();
    });
  }
}