package net.sf.anathema.framework.presenter;

import com.l2fprod.common.swing.JDirectoryChooser;
import net.sf.anathema.framework.presenter.action.preferences.IAnathemaPreferencesConstants;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import java.awt.Component;
import java.awt.Dimension;
import java.io.File;
import java.nio.file.Path;
import java.util.prefs.Preferences;

public class DirectoryFileChooser {
  
  public static Path createDirectoryChooser(Path startDirectory, String okayButtonString) {
    JDirectoryChooser chooser = new JDirectoryChooser(startDirectory.toFile());
    chooser.setMultiSelectionEnabled(false);
    Dimension size = chooser.getPreferredSize();
    size.setSize( size.width*1.5, size.height*1.5 );
    chooser.setPreferredSize( size );
    int selected = chooser.showDialog(null, okayButtonString);
    if (selected == JFileChooser.APPROVE_OPTION) {
      return chooser.getSelectedFile().toPath();
    } else {
      return null;
    }
  }

  public static Path createMusicDirectoryChooser(String preferencesValue, String okayButtonString) {
    File startDirectory = getStartDirectory(preferencesValue);
    JDirectoryChooser chooser = new JDirectoryChooser(startDirectory);
    chooser.setMultiSelectionEnabled(false);
    int selected = chooser.showDialog(null, okayButtonString);
    if (selected == JFileChooser.APPROVE_OPTION) {
      Path selectedFile = chooser.getSelectedFile().toPath();
      setChooserDirectory(preferencesValue, selectedFile);
      return selectedFile;
    }
    return null;
  }

  private static File getStartDirectory(String preferencesValue) {
    Preferences directoryChooserNode = getChooserPreferences();
    String fileString = directoryChooserNode.get(preferencesValue, null);
    File startDirectory = null;
    if (fileString != null) {
      startDirectory = new File(fileString);
    }
    return startDirectory;
  }

  private static Preferences getChooserPreferences() {
    return Preferences.userRoot().node(IAnathemaPreferencesConstants.USER_MEMORY_NODE).node(
        IAnathemaPreferencesConstants.DIRECTORY_CHOOSER_NODE);
  }

  public static Path chooseSingleFile(
      Component parentComponent,
      String preferencesValue,
      String okayButtonString,
      FileFilter filter) {
    File startDirectory = getStartDirectory(preferencesValue);
    JFileChooser chooser = new JFileChooser(startDirectory);
    chooser.setFileFilter(filter);
    chooser.setMultiSelectionEnabled(false);
    int selected = chooser.showDialog(parentComponent, okayButtonString);
    if (selected == JFileChooser.APPROVE_OPTION) {
      Path selectedFile = chooser.getSelectedFile().toPath();
      setChooserDirectory(preferencesValue, selectedFile.getParent());
      return selectedFile;
    }
    return null;
  }

  private static void setChooserDirectory(String preferencesValue, Path directory) {
    String parentDirectory = directory.toAbsolutePath().toString();
    getChooserPreferences().put(preferencesValue, parentDirectory);
  }
}