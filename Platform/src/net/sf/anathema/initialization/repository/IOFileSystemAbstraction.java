package net.sf.anathema.initialization.repository;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class IOFileSystemAbstraction implements IFileSystemAbstraction {

  public void createFolder(File folder) throws IOException {
    FileUtils.forceMkdir(folder);
  }

  public boolean canWrite(File file) {
    return file.canWrite();
  }

  public boolean canRead(File file) {
    return file.canRead();
  }

  public boolean exists(File file) {
    return file.exists();
  }
}