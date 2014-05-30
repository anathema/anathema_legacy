package net.sf.anathema.framework.presenter.action.menu.help.updatecheck;

import de.idos.updates.Version;
import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.framework.presenter.action.menu.help.IMessageData;
import net.sf.anathema.framework.presenter.action.menu.help.MessageData;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.gui.dialog.userdialog.page.AbstractDialogPage;
import net.sf.anathema.lib.message.BasicMessage;
import net.sf.anathema.lib.message.IBasicMessage;
import net.sf.anathema.lib.message.MessageType;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class UpdateDialogPage extends AbstractDialogPage {
  private final JLabel latestVersionLabel = new JLabel();
  private final JButton updateButton = new JButton("Install update");
  private final Resources resources;
  private IMessageData messageData;
  private final JProgressBar updateProgress = new JProgressBar();
  private final JProgressBar fileProgress = new JProgressBar();
  private JTextArea changelogDisplay = new JTextArea("Loading changelog...", 10, 0);
  private String description;
  private JLabel installedVersionLabel = new JLabel();

  public UpdateDialogPage(Resources resources) {
    super(resources.getString("Help.UpdateCheck.Checking"));
    this.resources = resources;
    this.updateProgress.setStringPainted(true);
  }

  @Override
  public JComponent createContent() {
    JPanel panel = new JPanel(new MigLayout(new LC().wrapAfter(2).insets("0", "0", "0", "15").fill()));
    panel.add(new JLabel(getString("Help.UpdateCheck.CurrentVersion") + ":"));
    panel.add(installedVersionLabel);
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
    return new BasicMessage(messageData.getMessage(), messageData.getType());
  }

  @Override
  public String getDescription() {
    return description;
  }

  @Override
  public String getTitle() {
    return getString("Help.UpdateCheck.Title");
  }

  public void showChangelog(String changelog) {
    changelogDisplay.setText(changelog);
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
    SwingUtilities.invokeLater(() -> updateProgress.setMaximum(numberOfElements));
  }

  public void showFilesAlreadyLoaded(final int numberOfElements) {
    SwingUtilities.invokeLater(() -> updateProgress.setValue(numberOfElements));
  }

  public void showLatestVersion(final String versionText) {
    SwingUtilities.invokeLater(() -> latestVersionLabel.setText(versionText));
  }

  private void changeButtonState(final boolean enabled) {
    SwingUtilities.invokeLater(() -> updateButton.setEnabled(enabled));
  }

  public void showExpectedFileSize(final int size) {
    SwingUtilities.invokeLater(() -> fileProgress.setMaximum(size));
  }

  public void showProgressOnFile(final int progress) {
    SwingUtilities.invokeLater(() -> fileProgress.setValue(progress));
  }

  public void showDescription(String message) {
    this.description = message;
      //TODO: Trigger update
  }

  public void showNoMessage() {
    this.messageData = null;
  }

  public void showMessage(String message, MessageType type) {
    this.messageData = new MessageData(message, type);
  }

  public void showProgressMessage(String message) {
    SwingUtilities.invokeLater(() -> updateProgress.setString(message));
  }

  public void showInstalledVersion(Version installedVersion) {
    installedVersionLabel.setText(installedVersion.asString());
  }

  public Tool addTool() {
    return new SwingTool(updateButton);
  }
}