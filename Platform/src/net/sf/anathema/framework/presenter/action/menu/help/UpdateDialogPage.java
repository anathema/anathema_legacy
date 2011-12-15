package net.sf.anathema.framework.presenter.action.menu.help;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.disy.commons.core.message.BasicMessage;
import net.disy.commons.core.message.IBasicMessage;
import net.disy.commons.swing.dialog.userdialog.page.AbstractDialogPage;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.IGridDialogLayoutData;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.resources.IResources;

public class UpdateDialogPage extends AbstractDialogPage {

  private final IResources resources;
  private final IUpdateChecker checker;
  private final JLabel latestVersionLabel = new JLabel("?.??"); //$NON-NLS-1$

  public UpdateDialogPage(IResources resources, final IUpdateChecker checker) {
    super(resources.getString("Help.UpdateCheck.Checking")); //$NON-NLS-1$
    this.resources = resources;
    this.checker = checker;
    checker.addDataChangedListener(new IChangeListener() {
      public void changeOccured() {
        updateLatestVersionLabel();
      }
    });
    updateLatestVersionLabel();
  }

  private void updateLatestVersionLabel() {
    String latestVersion = checker.getLatestVersion();
    latestVersionLabel.setText(latestVersion != null ? latestVersion : "?.??"); //$NON-NLS-1$
  }

  public JComponent createContent() {
    JPanel panel = new JPanel(new GridDialogLayout(2, false));
    panel.add(new JLabel(getString("Help.UpdateCheck.CurrentVersion") + ":"), IGridDialogLayoutData.DEFAULT); //$NON-NLS-1$ //$NON-NLS-2$
    panel.add(new JLabel(checker.getCurrentVersion()), IGridDialogLayoutData.DEFAULT);
    panel.add(new JLabel(getString("Help.UpdateCheck.LatestVersion") + ":"), IGridDialogLayoutData.DEFAULT); //$NON-NLS-1$ //$NON-NLS-2$
    panel.add(latestVersionLabel, IGridDialogLayoutData.DEFAULT);
    return panel;
  }

  public IBasicMessage createCurrentMessage() {
    IMessageData messageData = checker.getMessageData();
    if (messageData == null) {
      return getDefaultMessage();
    }
    return new BasicMessage(getString(messageData.getKey()), messageData.getType());
  }

  @Override
  public String getDescription() {
    Boolean success = checker.isCheckSuccessful();
    if (success == null) {
      return getString("Help.UpdateCheck.Checking"); //$NON-NLS-1$
    }
    else if (success) {
      return getString("Help.UpdateCheck.Success"); //$NON-NLS-1$
    }
    return getString("Help.UpdateCheck.Failure"); //$NON-NLS-1$
  }

  public String getTitle() {
    return getString("Help.UpdateCheck.Title"); //$NON-NLS-1$
  }

  private String getString(String key) {
    return resources.getString(key);
  }
}