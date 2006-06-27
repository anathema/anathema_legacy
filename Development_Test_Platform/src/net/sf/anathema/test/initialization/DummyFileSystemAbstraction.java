package net.sf.anathema.test.initialization;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.initialization.repository.IFileSystemAbstraction;

public class DummyFileSystemAbstraction implements IFileSystemAbstraction {

  private final List<File> existingFiles = new ArrayList<File>();
  private final List<File> createdFolders = new ArrayList<File>();
  private final List<File> writeProtectedFiles = new ArrayList<File>();
  private final List<File> readProtectedFiles = new ArrayList<File>();

  public boolean wasCreated(File file) {
    return createdFolders.contains(file);
  }

  public void createFolder(File folder) {
    createdFolders.add(folder);
  }

  public boolean canWrite(File file) {
    return !writeProtectedFiles.contains(file);
  }

  public boolean canRead(File file) {
    return !readProtectedFiles.contains(file);
  }

  public void addWriteProtectedFile(File file) {
    writeProtectedFiles.add(file);
  }

  public void addReadProtectedFile(File file) {
    readProtectedFiles.add(file);
  }

  public boolean exists(File file) {
    return existingFiles.contains(file) || createdFolders.contains(file);
  }

  public void addExistingFile(File file) {
    existingFiles.add(file);
  }
}