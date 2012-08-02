package net.sf.anathema.framework.presenter.action.menu.help.updatecheck;

import de.idos.updates.Version;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutDataBuilder;
import net.sf.anathema.framework.presenter.action.menu.help.IMessageData;
import net.sf.anathema.framework.presenter.action.menu.help.MessageData;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.gui.dialog.userdialog.page.AbstractDialogPage;
import net.sf.anathema.lib.message.BasicMessage;
import net.sf.anathema.lib.message.IBasicMessage;
import net.sf.anathema.lib.resources.IResources;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

import static net.disy.commons.swing.layout.grid.IGridDialogLayoutData.DEFAULT;
import static net.sf.anathema.framework.presenter.action.menu.help.updatecheck.UpdateAction.AUTO_UPDATE_ENABLED;
import static net.sf.anathema.lib.message.MessageType.ERROR;
import static net.sf.anathema.lib.message.MessageType.INFORMATION;

public class UpdateDialogPage extends AbstractDialogPage {

  private final JLabel latestVersionLabel = new JLabel("?.?.?"); //$NON-NLS-1$
  private final JButton updateButton = new JButton("Install update");
  private final IResources resources;
  private final Version installedVersion;
  private UpdateState state = UpdateState.Checking;
  private IMessageData messageData;
  private final JProgressBar updateProgress = new JProgressBar();


  public UpdateDialogPage(IResources resources, Version installedVersion) {
    super(resources.getString("Help.UpdateCheck.Checking")); //$NON-NLS-1$
    this.resources = resources;
    this.installedVersion = installedVersion;
    this.updateProgress.setStringPainted(true);
  }

  @Override
  public JComponent createContent() {
    JPanel panel = new JPanel(new GridDialogLayout(2, false));
    panel.add(new JLabel(getString("Help.UpdateCheck.CurrentVersion") + ":"), DEFAULT);
    panel.add(new JLabel(installedVersion.asString()), DEFAULT);
    panel.add(new JLabel(getString("Help.UpdateCheck.LatestVersion") + ":"), DEFAULT);
    panel.add(latestVersionLabel, DEFAULT);
    if (AUTO_UPDATE_ENABLED) {
      panel.add(updateButton);
      updateButton.setEnabled(false);
      panel.add(updateProgress, new GridDialogLayoutDataBuilder().filledHorizontal().grabExcessHorizontalSpace().get());
    }
    return panel;
  }

  @Override
  public IBasicMessage createCurrentMessage() {
    if (messageData == null) {
      return getDefaultMessage();
    }
    return new BasicMessage(getString(messageData.getKey()), messageData.getType());
  }

  @Override
  public String getDescription() {
    switch (state) {
      case Checking:
        return getString("Help.UpdateCheck.Checking"); //$NON-NLS-1$
      case CheckSuccessful:
        return getString("Help.UpdateCheck.Success"); //$NON-NLS-1$
      case CheckFailed:
        return getString("Help.UpdateCheck.Failure"); //$NON-NLS-1$
      case InstallationRunning:
        return getString("Help.UpdateInstallation.Running"); //$NON-NLS-1$
      case InstallationDone:
        return getString("Help.UpdateInstallation.Completed"); //$NON-NLS-1$
      default:
        throw new IllegalStateException("Unknown state");
    }
  }

  @Override
  public String getTitle() {
    return getString("Help.UpdateCheck.Title"); //$NON-NLS-1$
  }

  public void setErrorState(String key) {
    showUnknownVersion();
    this.state = UpdateState.CheckFailed;
    this.messageData = new MessageData(key, ERROR);
  }

  public void setSuccessState(String key, Version latestVersion) {
    showLatestVersion(latestVersion.asString());
    this.state = UpdateState.CheckSuccessful;
    this.messageData = new MessageData(key, INFORMATION);
  }

  public void clearState() {
    showUnknownVersion();
    this.state = UpdateState.Checking;
    this.messageData = null;
  }

  private void showUnknownVersion() {
    showLatestVersion("?.?.?");
  }

  public void whenUpdateIsRequestedDo(SmartAction smartAction) {
    updateButton.setAction(smartAction);
  }

  public void showInstallationRunning() {
    state = UpdateState.InstallationRunning;
  }

  public void enableUpdate() {
    changeButtonState(true);
  }

  public void disableUpdate() {
    changeButtonState(false);
  }

  private String getString(String key) {
    return resources.getString(key);
  }

  public void showFilesToDownload(final int numberOfElements) {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        updateProgress.setMaximum(numberOfElements);
      }
    });
  }

  public void showFilesAlreadyLoaded(final int numberOfElements) {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        updateProgress.setValue(numberOfElements);
      }
    });
  }

  public void showInstallationDone() {
    this.state = UpdateState.InstallationDone;
    this.messageData = new MessageData("Help.UpdateInstallation.RestartRequired", INFORMATION);
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        updateProgress.setString(getString("Help.UpdateInstallation.Done"));
      }
    });
  }

  private void showLatestVersion(final String versionText) {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        latestVersionLabel.setText(versionText);
      }
    });
  }

  private void changeButtonState(final boolean enabled) {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        updateButton.setEnabled(enabled);
      }
    });
  }
}