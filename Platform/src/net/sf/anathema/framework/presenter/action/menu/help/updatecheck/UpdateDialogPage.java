package net.sf.anathema.framework.presenter.action.menu.help.updatecheck;

import de.idos.updates.UpdateSystem;
import de.idos.updates.Version;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.IGridDialogLayoutData;
import net.sf.anathema.framework.presenter.action.menu.help.IMessageData;
import net.sf.anathema.framework.presenter.action.menu.help.MessageData;
import net.sf.anathema.lib.gui.dialog.userdialog.page.AbstractDialogPage;
import net.sf.anathema.lib.message.BasicMessage;
import net.sf.anathema.lib.message.IBasicMessage;
import net.sf.anathema.lib.resources.IResources;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import static net.sf.anathema.lib.message.MessageType.ERROR;
import static net.sf.anathema.lib.message.MessageType.INFORMATION;

public class UpdateDialogPage extends AbstractDialogPage {

  private final IResources resources;
  private UpdateSystem system;
  private Boolean successful;
  private final JLabel latestVersionLabel = new JLabel("?.??"); //$NON-NLS-1$
  private IMessageData messageData;


  public UpdateDialogPage(IResources resources, UpdateSystem system) {
    super(resources.getString("Help.UpdateCheck.Checking")); //$NON-NLS-1$
    this.resources = resources;
    this.system = system;
    system.reportAllProgressTo(new UiProgressReport(this));
  }

  @Override
  public JComponent createContent() {
    JPanel panel = new JPanel(new GridDialogLayout(2, false));
    panel.add(new JLabel(getString("Help.UpdateCheck.CurrentVersion") + ":"),
            IGridDialogLayoutData.DEFAULT); //$NON-NLS-1$ //$NON-NLS-2$
    panel.add(new JLabel(system.getInstalledVersion().asString()), IGridDialogLayoutData.DEFAULT);
    panel.add(new JLabel(getString("Help.UpdateCheck.LatestVersion") + ":"),
            IGridDialogLayoutData.DEFAULT); //$NON-NLS-1$ //$NON-NLS-2$
    panel.add(latestVersionLabel, IGridDialogLayoutData.DEFAULT);
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
    latestVersionLabel.setText("?.?.?");
    this.successful = false;
    this.messageData = new MessageData(key, ERROR);
  }

  public void setSuccessState(String key, Version latestVersion) {
    latestVersionLabel.setText(latestVersion.asString());
    this.successful = true;
    this.messageData = new MessageData(key, INFORMATION);
  }

  public void clearState(){
    latestVersionLabel.setText("?.?.?");
    this.successful = null;
    this.messageData = null;
  }
}