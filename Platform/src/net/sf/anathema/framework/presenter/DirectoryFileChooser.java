package net.sf.anathema.framework.presenter;

import java.awt.Component;
import java.io.File;
import java.util.prefs.Preferences;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import net.sf.anathema.framework.presenter.action.preferences.IAnathemaPreferencesConstants;

import com.l2fprod.common.swing.JDirectoryChooser;

public class DirectoryFileChooser {

  public static final String ADD_MUSIC_CHOOSER_VALUE = "AddMusicFolder"; //$NON-NLS-1$

  public static File createDirectoryChooser(String preferencesValue, String okayButtonString) {
    File startDirectory = getStartDirectory(preferencesValue);
    JDirectoryChooser chooser = new JDirectoryChooser(startDirectory);
    chooser.setMultiSelectionEnabled(false);
    int selected = chooser.showDialog(null, okayButtonString);
    if (selected == JFileChooser.APPROVE_OPTION) {
      File selectedFile = chooser.getSelectedFile();
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

  public static File chooseSingleFile(
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
      File selectedFile = chooser.getSelectedFile();
      setChooserDirectory(preferencesValue, selectedFile.getParentFile());
      return selectedFile;
    }
    return null;
  }

  private static void setChooserDirectory(String preferencesValue, File directoryFile) {
    String parentDirectory = directoryFile.getAbsolutePath();
    getChooserPreferences().put(preferencesValue, parentDirectory);
  }

  public static File[] chooseMultipleFiles(
      Component parentComponent,
      String preferencesValue,
      String okayButtonString,
      FileFilter filter) {
    File startDirectory = getStartDirectory(preferencesValue);
    JFileChooser chooser = new JFileChooser(startDirectory);
    chooser.setFileFilter(filter);
    chooser.setMultiSelectionEnabled(true);
    int selected = chooser.showDialog(parentComponent, okayButtonString);
    if (selected == JFileChooser.APPROVE_OPTION) {
      File[] selectedFiles = chooser.getSelectedFiles();
      if (selectedFiles.length == 0) {
        return new File[0];
      }
      File chooserDirectory = selectedFiles[0].getParentFile();
      setChooserDirectory(preferencesValue, chooserDirectory);
      return selectedFiles;
    }
    return new File[0];
  }
}