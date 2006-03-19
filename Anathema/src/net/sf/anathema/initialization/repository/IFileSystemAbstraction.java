package net.sf.anathema.initialization.repository;

import java.io.File;
import java.io.IOException;

public interface IFileSystemAbstraction {

  public void createFolder(File folder) throws IOException;

  public boolean canWrite(File file);

  public boolean canRead(File file);

  public boolean exists(File file);
}