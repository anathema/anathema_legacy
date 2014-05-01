package net.sf.anathema.framework.repository.tree;

import javafx.stage.Window;
import net.sf.anathema.lib.gui.file.Extension;
import net.sf.anathema.lib.gui.file.FileChooserConfiguration;

import java.nio.file.Path;

public class FileChoosingUtilities {

  public static Path selectSaveFile(Window parent, String suggestedFileName, Extension defaultExtension) {
    return new FxFileChooser(parent).selectSaveFile(new FileChooserConfiguration(defaultExtension.description, defaultExtension.pattern, suggestedFileName));
  }

  public static Path chooseFile(Window parent, Extension extension) {
    return new FxFileChooser(parent).chooseLoadFile(extension);
  }
}