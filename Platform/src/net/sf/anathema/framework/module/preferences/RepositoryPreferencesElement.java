package net.sf.anathema.framework.module.preferences;

import static net.sf.anathema.framework.presenter.action.preferences.IAnathemaPreferencesConstants.DEFAULT_REPOSITORY_LOCATION;
import static net.sf.anathema.framework.presenter.action.preferences.IAnathemaPreferencesConstants.REPOSITORY_PREFERENCE;

import java.awt.Component;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.disy.commons.core.message.Message;
import net.disy.commons.swing.action.SmartAction;
import net.disy.commons.swing.dialog.message.MessageDialogFactory;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.IDialogComponent;
import net.sf.anathema.framework.presenter.DirectoryFileChooser;
import net.sf.anathema.framework.presenter.action.preferences.IPreferencesElement;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;

public class RepositoryPreferencesElement implements IPreferencesElement {

  private static final String REPOSITORY_PREFERENCE_DIRECTORY_CHOOSER_VALUE = "RepositoryPreference"; //$NON-NLS-1$
  private File repositoryDirectory = new File(IPreferencesElement.SYSTEM_PREFERENCES.get(
      REPOSITORY_PREFERENCE,
      DEFAULT_REPOSITORY_LOCATION));
  private File defaultFile = new File(DEFAULT_REPOSITORY_LOCATION);
  private boolean dirty;
  boolean modificationAllowed = false;
  private IResources resources;
  private JTextField repositoryTextField;

  public IDialogComponent getComponent(final IResources resource) {
    this.resources = resource;
    final JLabel repositoryLabel = new JLabel(
        resource.getString("AnathemaCore.Tools.Preferences.RepositoryDirectory.Label") + ":"); //$NON-NLS-1$ //$NON-NLS-2$
    repositoryTextField = new JTextField(45);
    repositoryTextField.setEditable(false);
    setDisplayedPath(repositoryTextField, repositoryDirectory);
    final JButton browseButton = createBrowseButton();
    modificationAllowed = true;
    return new IDialogComponent() {
      public void fillInto(JPanel panel, int columnCount) {
        panel.add(repositoryLabel);
        GridDialogLayoutData data = new GridDialogLayoutData();
        data.setHorizontalSpan(columnCount - 1);
        panel.add(browseButton, data);
        GridDialogLayoutData singleElementData = new GridDialogLayoutData(GridDialogLayoutData.FILL_HORIZONTAL);
        singleElementData.setHorizontalSpan(columnCount);
        panel.add(repositoryTextField, singleElementData);
      }

      public int getColumnCount() {
        return 2;
      }
    };
  }

  private void setDisplayedPath(final JTextField repositoryTextField, File selectedDirectory) {
    try {
      String displayedPath = selectedDirectory.getAbsolutePath();
      if (repositoryDirectory.getCanonicalFile().equals(defaultFile.getCanonicalFile())) {
        displayedPath = createDefaultString();
      }
      repositoryTextField.setText(displayedPath);
    }
    catch (IOException e) {
      Throwable cause = e.getCause();
      MessageDialogFactory.showMessageDialog(null, new Message(
          "An error occured while setting the repository path: " + cause.getMessage(), cause)); //$NON-NLS-1$
    }
  }

  private String createDefaultString() {
    String displayedPath;
    displayedPath = "[" + resources.getString("AnathemaCore.Tools.Preferences.RepositoryDirectory.DefaultRepository") + "]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    return displayedPath;
  }

  private JButton createBrowseButton() {
    return new JButton(new SmartAction(
        resources.getString("AnathemaCore.Tools.Preferences.RepositoryDirectory.BrowseButton")) { //$NON-NLS-1$
          @Override
          protected void execute(Component parent) {
            File selectedDir = DirectoryFileChooser.createDirectoryChooser(
                REPOSITORY_PREFERENCE_DIRECTORY_CHOOSER_VALUE,
                resources.getString("UserDialog.OkayButton.Text")); //$NON-NLS-1$
            if (selectedDir != null) {
              setDisplayedPath(repositoryTextField, selectedDir);
              repositoryDirectory = selectedDir;
              dirty = modificationAllowed;
            }
          }
        });
  }

  public void savePreferences() {
    try {
      if (repositoryDirectory.getCanonicalFile().equals(defaultFile.getCanonicalFile())) {
        SYSTEM_PREFERENCES.remove(REPOSITORY_PREFERENCE);
      }
      else {
        SYSTEM_PREFERENCES.put(REPOSITORY_PREFERENCE, repositoryDirectory.getAbsolutePath());
      }
    }
    catch (IOException e) {
      Throwable cause = e.getCause();
      MessageDialogFactory.showMessageDialog(null, new Message(
          "An error occured while saving the repository preferences: " + cause.getMessage(), cause)); //$NON-NLS-1$
    }
  }

  public boolean isDirty() {
    return dirty;
  }

  public IIdentificate getCategory() {
    return SYSTEM_CATEGORY;
  }

  public void reset() {
    repositoryDirectory = new File(IPreferencesElement.SYSTEM_PREFERENCES.get(
        REPOSITORY_PREFERENCE,
        DEFAULT_REPOSITORY_LOCATION));
    setDisplayedPath(repositoryTextField, repositoryDirectory);
    dirty = false;
  }
}