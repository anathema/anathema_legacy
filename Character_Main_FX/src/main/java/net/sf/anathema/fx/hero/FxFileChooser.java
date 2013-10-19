package net.sf.anathema.fx.hero;

import javafx.stage.FileChooser;
import net.sf.anathema.hero.framework.perspective.sheet.FileChooserConfiguration;
import net.sf.anathema.hero.framework.perspective.sheet.RegisteredFileChooser;
import net.sf.anathema.hero.framework.perspective.sheet.SingleFileChooser;

import java.io.File;
import java.nio.file.Path;

@RegisteredFileChooser
public class FxFileChooser implements SingleFileChooser {

  public Path selectSaveFile(FileChooserConfiguration configuration) {
    FileChooser chooser = new javafx.stage.FileChooser();
    FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter(configuration.description, configuration.filter);
    chooser.getExtensionFilters().add(filter);
    File file = chooser.showSaveDialog(null);
    if (file == null) {
      return null;
    }
    return file.toPath();
  }
}