package net.sf.anathema.framework.view;

import java.io.File;

import net.sf.anathema.framework.item.IItemType;

public class PrintNameFile {

  private final File file;
  private final String printName;
  private final String repositoryId;
  private final IItemType itemType;

  public PrintNameFile(File file, String printName, IItemType itemType) {
    this(file, printName, extractRepositoryIdFromFile(file), itemType);
  }

  private PrintNameFile(File file, String printName, String repositoryId, IItemType itemType) {
    this.file = file;
    this.printName = printName;
    this.itemType = itemType;
    this.repositoryId = repositoryId;
  }

  private static String extractRepositoryIdFromFile(File file) {
    String fileName = file.getName();
    int extensionIndex = fileName.lastIndexOf('.');
    return extensionIndex >= 0
      ? fileName.substring(0, extensionIndex)
      : fileName;
  }

  public PrintNameFile getParent() {
    return new PrintNameFile(file.getParentFile(), printName, repositoryId, itemType);
  }

  public File getFile() {
    return file;
  }

  public String getPrintName() {
    return printName;
  }

  @Override
  public String toString() {
    return printName;
  }

  public String getRepositoryId() {
    return repositoryId;
  }

  public IItemType getItemType() {
    return itemType;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof PrintNameFile)) {
      return false;
    }
    PrintNameFile objFile = (PrintNameFile) obj;
    return this.repositoryId.equals(objFile.getRepositoryId()) && this.itemType.equals(objFile.getItemType());
  }

  @Override
  public int hashCode() {
    return repositoryId.length() * 23 + printName.length() * 19;
  }
}
