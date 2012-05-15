package net.sf.anathema.initialization.repository;

import java.io.File;
import java.io.IOException;

public interface IFileSystemAbstraction {

  void createFolder(File folder) throws IOException;

  boolean canWrite(File file);

  boolean canRead(File file);

  boolean exists(File file);
}