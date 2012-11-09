package net.sf.anathema.lib.gui.file;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.plaf.FileChooserUI;
import javax.swing.plaf.basic.BasicFileChooserUI;
import java.awt.Component;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileChoosingUtilities {

  private static Path workingDirectory = Paths.get(".");

  public static Path selectSaveFile(Component parentComponent, String suggestedFileName) {
    JFileChooser chooser = new JFileChooser(workingDirectory.toFile());
    FileChooserUI chooserUi = chooser.getUI();
    if (chooserUi instanceof BasicFileChooserUI) {
      BasicFileChooserUI basicUi = (BasicFileChooserUI) chooserUi;
      basicUi.setFileName(suggestedFileName);
    }
    int approveOption = chooser.showSaveDialog(parentComponent);
    if (approveOption != JFileChooser.APPROVE_OPTION) {
      return null;
    }
    Path selectedFile = chooser.getSelectedFile().toPath();
    workingDirectory = selectedFile.getParent();
    return selectedFile;
  }

  public static Path chooseFile(String confirm, Component parentComponent, FileFilter filter) {
    JFileChooser chooser = new JFileChooser();
    chooser.setFileFilter(filter);
    chooser.showDialog(parentComponent, confirm);
    return chooser.getSelectedFile().toPath();
  }
}