package net.sf.anathema.framework.repository.tree;

import javafx.stage.FileChooser;
import javafx.stage.Window;
import net.sf.anathema.lib.gui.file.Extension;
import net.sf.anathema.lib.gui.file.FileChooserConfiguration;
import net.sf.anathema.lib.gui.file.SingleFileChooser;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FxFileChooser implements SingleFileChooser {

  public static final Path NO_FILE_SELECTED = null;
  private static Path workingDirectory = Paths.get(".");
  private final Window parent;

  @SuppressWarnings("UnusedDeclaration")
  public FxFileChooser() {
    this(null);
  }

  public FxFileChooser(Window parent) {
    this.parent = parent;
  }

  public Path selectSaveFile(FileChooserConfiguration configuration) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setInitialDirectory(workingDirectory.toFile());
    fileChooser.setInitialFileName(configuration.nameSuggestion);
    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(configuration.extension.description, configuration.extension.pattern));
    File file = fileChooser.showSaveDialog(parent);
    if (file == null) {
      return NO_FILE_SELECTED;
    }
    Path selectedFile = file.toPath();
    workingDirectory = selectedFile.getParent();
    return selectedFile;
  }

  @Override
  public Path selectLoadFile(Extension extension) {
    FileChooser chooser = new FileChooser();
    chooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter(extension.description, extension.pattern));
    chooser.setInitialDirectory(workingDirectory.toFile());
    File file = chooser.showOpenDialog(parent);
    if (file == null) {
      return NO_FILE_SELECTED;
    }
    Path selectedFile = file.toPath();
    workingDirectory = selectedFile.getParent();
    return selectedFile;
  }
}