package net.sf.anathema.framework.preferences.repository;

import net.miginfocom.layout.CC;
import net.sf.anathema.framework.configuration.InitializationPreferences;
import net.sf.anathema.framework.presenter.DirectoryFileChooser;
import net.sf.anathema.framework.presenter.action.preferences.IPreferencesElement;
import net.sf.anathema.framework.swing.ExceptionIndicator;
import net.sf.anathema.initialization.FxApplicationFrame;
import net.sf.anathema.initialization.PreferenceElement;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.initialization.repository.RepositoryLocationResolver;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.io.PathUtils;
import net.sf.anathema.lib.message.Message;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Component;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static net.sf.anathema.framework.presenter.action.preferences.IAnathemaPreferencesConstants.REPOSITORY_PREFERENCE;

@PreferenceElement
@Weight(weight = 80)
public class RepositoryPreferencesElement implements IPreferencesElement {
  private Path repositoryDirectory;
  private Path defaultDirectory;
  private RepositoryLocationResolver repository;
  private boolean dirty;
  private boolean modificationAllowed = false;
  private JTextField repositoryTextField;
  private Resources resources;

  public RepositoryPreferencesElement() {
    try {
      repository = new RepositoryLocationResolver(new InitializationPreferences());
      repositoryDirectory = new File(repository.resolve()).getCanonicalFile().toPath();
      defaultDirectory = new File(repository.getDefaultLocation()).getCanonicalFile().toPath();
      verifyDirectoriesExist();
    } catch (IOException e) {
      handleException(e);
    }
  }

  private void verifyDirectoriesExist() {
    try {
      if (!isValid() || !isDefaultValid()) {
        throw new IOException(
                "Unable to read/write/create user selected repository folder and default repository folder.");
      }
    } catch (IOException | AnathemaException ex) {
      handleException(ex);
    }
  }

  @Override
  public void addComponent(JPanel panel, Resources resource) {
    verifyDirectoriesExist();
    this.resources = resource;
    final JLabel repositoryLabel = new JLabel(resources.getString(
            "AnathemaCore.Tools.Preferences.RepositoryDirectory.Label") + ":");
    repositoryTextField = new JTextField(45);
    repositoryTextField.setEditable(false);
    setDisplayedPath(repositoryDirectory);
    final JButton browseButton = createBrowseButton();
    final JButton defaultButton = createDefaultButton();
    modificationAllowed = true;
    panel.add(repositoryLabel, new CC().pushX());
    panel.add(browseButton, new CC().alignX("right"));
    panel.add(repositoryTextField, new CC().spanX().growX().pushX());
    panel.add(defaultButton, new CC().pushX());
    if (Desktop.isDesktopSupported()) {
      JButton openButton = createOpenButton();
      panel.add(openButton, new CC().alignX("right"));
    }
  }

  private void setDisplayedPath(Path selectedDirectory) {
    repositoryTextField.setText(selectedDirectory.toAbsolutePath().toString());
  }

  private JButton createBrowseButton() {
    return new JButton(new SmartAction(
            resources.getString("AnathemaCore.Tools.Preferences.RepositoryDirectory.ChooseDirectory")) {
      @Override
      protected void execute(Component parent) {
        Path selectedDir = DirectoryFileChooser.createDirectoryChooser(repositoryDirectory,
                resources.getString("AnathemaCore.Tools.Preferences.RepositoryDirectory.ChooseDirectory"));
        if (selectedDir != null) {
          setDisplayedPath(selectedDir);
          repositoryDirectory = selectedDir;
          dirty = modificationAllowed;
        }
      }
    });
  }

  private JButton createDefaultButton() {
    return new JButton(new SmartAction(
            resources.getString("AnathemaCore.Tools.Preferences.RepositoryDirectory.DefaultDirectory")) {
      @Override
      protected void execute(Component parent) {
        repositoryDirectory = defaultDirectory.toAbsolutePath();
        setDisplayedPath(defaultDirectory);
        dirty = modificationAllowed;
      }
    });
  }

  private JButton createOpenButton() {
    return new JButton(
            new SmartAction(resources.getString("AnathemaCore.Tools.Preferences.RepositoryDirectory.OpenDirectory")) {
              @Override
              protected void execute(Component parent) {
                try {
                  if (Files.exists(repositoryDirectory)) {
                    PathUtils.openOnDesktop(repositoryDirectory);
                  } else if (Files.exists(repositoryDirectory.getParent())) {
                    PathUtils.openOnDesktop(repositoryDirectory.getParent());
                  } else {
                    throw new IOException(
                            "Repository directory (and parent directory) deleted by user while the preferences dialog was open");
                  }
                } catch (IOException e) {
                  handleException(e, "An error occured while opening the repository path: ");
                }
              }
            });
  }

  @Override
  public void savePreferences() {
    try {
      if (Files.isSameFile(repositoryDirectory, defaultDirectory)) {
        SYSTEM_PREFERENCES.remove(REPOSITORY_PREFERENCE);
      } else {
        SYSTEM_PREFERENCES.put(REPOSITORY_PREFERENCE, repositoryDirectory.toAbsolutePath().toString());
      }
    } catch (IOException e) {
      handleException(e, "An error occured while saving the repository preferences: ");
    }
  }

  @Override
  public boolean isValid() {
    try {
      return new RepositoryFolderWorker().isValid(repositoryDirectory.toFile());
    } catch (AnathemaException e) {
      handleException(e);
      return false;
    }
  }

  public boolean isDefaultValid() {
    return new RepositoryFolderWorker().isValid(defaultDirectory.toFile());
  }

  @Override
  public boolean isDirty() {
    return dirty;
  }

  @Override
  public Identifier getCategory() {
    return new SimpleIdentifier("Repository");
  }

  @Override
  public void reset() {
    repositoryDirectory = Paths.get(repository.resolve());
    setDisplayedPath(repositoryDirectory);
    dirty = false;
  }

  private void handleException(Exception e) {
    String messageText = "An error occured while setting up the repository paths: ";
    handleException(e, messageText);
  }

  private void handleException(Exception e, String messageText) {
    Throwable cause = e.getCause();
    if (cause == null) {
      cause = e;
    }
    Message message = new Message(messageText + cause.getMessage(),
            cause);
    ExceptionIndicator.indicate(resources, FxApplicationFrame.getOwner(), message);
  }
}