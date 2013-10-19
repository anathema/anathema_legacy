package net.sf.anathema.hero.framework.perspective.sheet;

import net.sf.anathema.framework.environment.Environment;

import java.nio.file.Path;
import java.util.Collection;

public class ControlledFileChooser implements FileChooser {

  private final Environment environment;
  private final FileChooserConfiguration configuration;

  public ControlledFileChooser(Environment environment, FileChooserConfiguration configuration) {
    this.environment = environment;
    this.configuration = configuration;
  }

  @Override
  public Path getPrintFile() {
    SingleFileChooser chooser = createFileChooser();
    Path selectedFile = chooser.selectSaveFile(configuration);
    if (selectedFile == null) {
      return null;
    }
    return selectedFile;
  }

  private SingleFileChooser createFileChooser() {
    Collection<SingleFileChooser> choosers = environment.instantiateAll(RegisteredFileChooser.class);
    return choosers.iterator().next();
  }
}