package net.sf.anathema.test.character;

import java.io.File;

import net.sf.anathema.initialization.repository.IDataFileProvider;

public class DemoDataFileProvider implements IDataFileProvider {

  private final File folder;

  public DemoDataFileProvider() {
    this.folder = new File("."); //$NON-NLS-1$
  }

  public File getDataBaseDirectory(String subfolder) {
    return folder;
  }
}