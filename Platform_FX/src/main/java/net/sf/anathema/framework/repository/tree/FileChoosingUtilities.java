package net.sf.anathema.framework.repository.tree;

import javafx.stage.FileChooser;
import javafx.stage.Window;
import net.sf.anathema.lib.gui.file.Extension;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileChoosingUtilities {

  public static final Path NO_FILE_SELECTED = null;
  private static Path workingDirectory = Paths.get(".");

  public static Path selectSaveFile(Window parent, String suggestedFileName, Extension defaultExtension) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setInitialDirectory(workingDirectory.toFile());
    fileChooser.setInitialFileName(suggestedFileName);
    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(defaultExtension.description, defaultExtension.pattern));
    File file = fileChooser.showSaveDialog(parent);
    if (file == null) {
      return NO_FILE_SELECTED;
    }
    Path selectedFile = file.toPath();
    workingDirectory = selectedFile.getParent();
    return selectedFile;
  }

  public static Path chooseFile(Window parent, Extension extension) {
    FileChooser chooser = new FileChooser();
    chooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter(extension.description, extension.pattern));
    File file = chooser.showOpenDialog(parent);
    if (file == null) {
      return NO_FILE_SELECTED;
    }
    return file.toPath();
  }
}