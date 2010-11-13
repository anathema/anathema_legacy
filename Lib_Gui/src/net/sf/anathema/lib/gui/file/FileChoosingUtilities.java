package net.sf.anathema.lib.gui.file;

import java.awt.Component;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.plaf.FileChooserUI;
import javax.swing.plaf.basic.BasicFileChooserUI;

public class FileChoosingUtilities {

  private static File workingDirectory = new File("."); //$NON-NLS-1$

  public static File selectSaveFile(Component parentComponent, String suggestedFileName) {
    JFileChooser chooser = new JFileChooser(workingDirectory);
    FileChooserUI chooserUi = chooser.getUI();
    if (chooserUi instanceof BasicFileChooserUI) {
      BasicFileChooserUI basicUi = (BasicFileChooserUI) chooserUi;
      basicUi.setFileName(suggestedFileName);
    }
    int approveOption = chooser.showSaveDialog(parentComponent);
    if (approveOption != JFileChooser.APPROVE_OPTION) {
      return null;
    }
    File selectedFile = chooser.getSelectedFile();
    workingDirectory = selectedFile.getParentFile();
    return selectedFile;
  }

  public static File chooseFile(String confirm, Component parentComponent, FileFilter filter) {
    return chooseFile(confirm, parentComponent, filter, null);
  }

  public static File chooseFile(String confirm, Component parentComponent, FileFilter filter, File startDirectory) {
    JFileChooser chooser = new JFileChooser(startDirectory);
    chooser.setFileFilter(filter);
    chooser.showDialog(parentComponent, confirm);
    return chooser.getSelectedFile();
  }

  public static File[] chooseFiles(String confirm, Component parentComponent, FileFilter filter, File startDirectory) {
    JFileChooser chooser = new JFileChooser(startDirectory);
    chooser.setMultiSelectionEnabled(true);
    chooser.setFileFilter(filter);
    chooser.showDialog(parentComponent, confirm);
    return chooser.getSelectedFiles();
  }
}