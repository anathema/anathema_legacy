package net.sf.anathema.initialization.repository;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class IOFileSystemAbstraction implements IFileSystemAbstraction {

  @Override
  public void createFolder(File folder) throws IOException {
    FileUtils.forceMkdir(folder);
  }

  @Override
  public boolean canWrite(File file) {
    return file.canWrite();
  }

  @Override
  public boolean canRead(File file) {
    return file.canRead();
  }

  @Override
  public boolean exists(File file) {
    return file.exists();
  }
}