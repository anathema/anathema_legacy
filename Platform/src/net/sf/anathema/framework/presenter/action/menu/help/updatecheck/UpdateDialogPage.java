package net.sf.anathema.framework.presenter.action.menu.help.updatecheck;

import de.idos.updates.Version;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
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

import static net.disy.commons.swing.layout.grid.IGridDialogLayoutData.DEFAULT;
import static net.sf.anathema.framework.presenter.action.menu.help.updatecheck.AnathemaUpdateAction.AUTO_UPDATE_ENABLED;
import static net.sf.anathema.lib.message.MessageType.ERROR;
import static net.sf.anathema.lib.message.MessageType.INFORMATION;

public class UpdateDialogPage extends AbstractDialogPage {

  private final JLabel latestVersionLabel = new JLabel("?.?.?"); //$NON-NLS-1$
  private final JButton updateButton = new JButton("Install update");
  private final IResources resources;
  private final Version installedVersion;
  private Boolean successful;
  private IMessageData messageData;


  public UpdateDialogPage(IResources resources, Version installedVersion) {
    super(resources.getString("Help.UpdateCheck.Checking")); //$NON-NLS-1$
    this.resources = resources;
    this.installedVersion = installedVersion;
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
    if (successful == null) {
      return getString("Help.UpdateCheck.Checking"); //$NON-NLS-1$
    } else if (successful) {
      return getString("Help.UpdateCheck.Success"); //$NON-NLS-1$
    }
    return getString("Help.UpdateCheck.Failure"); //$NON-NLS-1$
  }

  @Override
  public String getTitle() {
    return getString("Help.UpdateCheck.Title"); //$NON-NLS-1$
  }

  private String getString(String key) {
    return resources.getString(key);
  }

  public void setErrorState(String key) {
    showUnknownVersion();
    this.successful = false;
    this.messageData = new MessageData(key, ERROR);
  }

  public void setSuccessState(String key, Version latestVersion) {
    latestVersionLabel.setText(latestVersion.asString());
    this.successful = true;
    this.messageData = new MessageData(key, INFORMATION);
  }

  public void clearState() {
    showUnknownVersion();
    this.successful = null;
    this.messageData = null;
  }

  private void showUnknownVersion() {
    latestVersionLabel.setText("?.?.?");
  }

  public void whenUpdateIsRequestedDo(SmartAction smartAction) {
    updateButton.setAction(smartAction);
  }

  public void enableUpdate(boolean updateAvailable) {
    updateButton.setEnabled(updateAvailable);
  }
}