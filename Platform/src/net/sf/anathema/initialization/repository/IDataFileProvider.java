package net.sf.anathema.initialization.repository;

import java.nio.file.Path;

public interface IDataFileProvider {

  Path getDataBaseDirectory(String subfolderName);
}