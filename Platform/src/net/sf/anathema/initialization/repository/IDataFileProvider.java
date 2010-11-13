package net.sf.anathema.initialization.repository;

import java.io.File;

public interface IDataFileProvider {

  public File getDataBaseDirectory(String subfolderName);
}