package net.sf.anathema.framework.view;

import java.io.File;

import net.sf.anathema.framework.item.IItemType;

public class PrintNameFile {

  private final File file;
  private final String printName;
  private final String repositoryId;
  private final IItemType itemType;

  public PrintNameFile(File file, String printName, String repositoryId, IItemType itemType) {
    this.file = file;
    this.printName = printName;
    this.repositoryId = repositoryId;
    this.itemType = itemType;
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