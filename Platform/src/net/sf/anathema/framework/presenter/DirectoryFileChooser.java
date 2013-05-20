package net.sf.anathema.framework.presenter;

import javax.swing.JFileChooser;
import java.nio.file.Path;

import static javax.swing.JFileChooser.DIRECTORIES_ONLY;

public class DirectoryFileChooser {
  
  public static Path createDirectoryChooser(Path startDirectory, String okayButtonString) {
    JFileChooser fileChooser = new JFileChooser(startDirectory.toFile());
    fileChooser.setMultiSelectionEnabled(false);
    fileChooser.setApproveButtonText(okayButtonString);
    fileChooser.setFileSelectionMode(DIRECTORIES_ONLY);
    int selected = fileChooser.showDialog(null, okayButtonString);
    if (selected == JFileChooser.APPROVE_OPTION) {
      return fileChooser.getSelectedFile().toPath();
    } else {
      return null;
    }
  }
}