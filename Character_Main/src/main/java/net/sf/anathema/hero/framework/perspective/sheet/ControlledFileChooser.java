package net.sf.anathema.hero.framework.perspective.sheet;

import net.sf.anathema.framework.environment.ObjectFactory;

import java.nio.file.Path;

public class ControlledFileChooser implements FileChooser {

  private final ObjectFactory factory;
  private final FileChooserConfiguration configuration;

  public ControlledFileChooser(ObjectFactory factory, FileChooserConfiguration configuration) {
    this.factory = factory;
    this.configuration = configuration;
  }

  @Override
  public Path getPrintFile() {
    SingleFileChooser chooser = factory.instantiateOnlyImplementer(SingleFileChooser.class);
    Path selectedFile = chooser.selectSaveFile(configuration);
    if (selectedFile == null) {
      return null;
    }
    return selectedFile;
  }
}