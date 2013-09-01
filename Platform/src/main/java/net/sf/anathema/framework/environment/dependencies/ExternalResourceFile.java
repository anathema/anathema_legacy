package net.sf.anathema.framework.environment.dependencies;

import net.sf.anathema.framework.environment.resources.ResourceFile;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class ExternalResourceFile implements ResourceFile {
  private final File file;

  public ExternalResourceFile(File file) {
    this.file = file;
  }

  @Override
  public URL getURL() {
    try {
      return file.toURI().toURL();
    } catch (MalformedURLException e) {
      throw new RuntimeException("Could not load file " + file.getName());
    }
  }

  @Override
  public String getFileName() {
    return FilenameUtils.separatorsToUnix(file.getAbsolutePath());
  }
}