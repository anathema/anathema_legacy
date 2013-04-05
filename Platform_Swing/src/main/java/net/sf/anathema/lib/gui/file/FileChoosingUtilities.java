package net.sf.anathema.lib.gui.file;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.plaf.FileChooserUI;
import java.awt.Component;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileChoosingUtilities {

  private static Path workingDirectory = Paths.get(".");

  public static Path selectSaveFile(Component parentComponent, String suggestedFileName) {
    JFileChooser chooser = new JFileChooser(workingDirectory.toFile());
    setSuggestedFileName(suggestedFileName, chooser);
    int approveOption = chooser.showSaveDialog(parentComponent);
    if (approveOption != JFileChooser.APPROVE_OPTION) {
      return null;
    }
    Path selectedFile = chooser.getSelectedFile().toPath();
    workingDirectory = selectedFile.getParent();
    return selectedFile;
  }

  private static void setSuggestedFileName(String suggestedFileName, JFileChooser chooser) {
    FileChooserUI chooserUi = chooser.getUI();
    try {
      Method method = chooserUi.getClass().getMethod("setFileName", String.class);
      method.invoke(chooserUi, suggestedFileName);
    } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
      //Don't set the name, we can live with that.
    }
  }

  public static Path chooseFile(String confirm, Component parentComponent, FileFilter filter) {
    JFileChooser chooser = new JFileChooser();
    chooser.setFileFilter(filter);
    chooser.showDialog(parentComponent, confirm);
    return chooser.getSelectedFile().toPath();
  }
}