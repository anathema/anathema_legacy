package net.sf.anathema.initialization.repository;

import java.io.File;

public interface IDataFileProvider {

  File getDataBaseDirectory(String subfolderName);
}