package net.sf.anathema.hero.framework.perspective.sheet;

import net.sf.anathema.lib.gui.file.FileChooserConfiguration;
import net.sf.anathema.lib.gui.file.SingleFileChooser;

import java.nio.file.Path;

public class ControlledFileChooser implements FileChooser {

  private final SingleFileChooser chooser;
  private final FileChooserConfiguration configuration;

  public ControlledFileChooser(SingleFileChooser chooser, FileChooserConfiguration configuration) {
    this.chooser = chooser;
    this.configuration = configuration;
  }

  @Override
  public Path getPrintFile() {
    Path selectedFile = chooser.selectSaveFile(configuration);
    if (selectedFile == null) {
      return null;
    }
    return selectedFile;
  }
}