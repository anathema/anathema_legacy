package net.sf.anathema.framework.presenter.action.menu.help.updatecheck;

import de.idos.updates.Version;
import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;
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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

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
  private final JProgressBar fileProgress = new JProgressBar();
  private JTextArea changelogDisplay = new JTextArea("Loading changelog...", 10, 0);

  public UpdateDialogPage(IResources resources, Version installedVersion) {
    super(resources.getString("Help.UpdateCheck.Checking")); //$NON-NLS-1$
    this.resources = resources;
    this.installedVersion = installedVersion;
    this.updateProgress.setStringPainted(true);
  }

  @Override
  public JComponent createContent() {
    JPanel panel = new JPanel(new MigLayout(new LC().wrapAfter(2).insets("0", "0", "0", "15").fill()));
    panel.add(new JLabel(getString("Help.UpdateCheck.CurrentVersion") + ":"));
    panel.add(new JLabel(installedVersion.asString()));
    panel.add(new JLabel(getString("Help.UpdateCheck.LatestVersion") + ":"));
    panel.add(latestVersionLabel);
    panel.add(new JScrollPane(changelogDisplay), new CC().growX().spanX());
    changelogDisplay.setEditable(false);
    panel.add(updateButton, new CC().spanY(2).grow());
    updateButton.setEnabled(false);
    panel.add(updateProgress, new CC().grow().push());
    panel.add(fileProgress, new CC().grow().push());
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

  public void showChangelog(String changelog) {
    changelogDisplay.setText(changelog);
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

  private void showUnknownVersion() {
    showLatestVersion("?.?.?");
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

  public void showExpectedFileSize(final int size) {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        fileProgress.setMaximum(size);
      }
    });
  }

  public void showProgressOnFile(final int progress) {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        fileProgress.setValue(progress);
      }
    });
  }
}