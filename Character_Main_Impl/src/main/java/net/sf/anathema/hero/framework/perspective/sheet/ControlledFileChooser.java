package net.sf.anathema.hero.framework.perspective.sheet;

import java.nio.file.Path;

public class ControlledFileChooser implements FileChooser {

  @Override
  public Path getPrintFile() {
    Path selectedFile = FxFileChooser.selectSaveFile();
    if (selectedFile == null) {
      return null;
    }
    return selectedFile;
  }
}