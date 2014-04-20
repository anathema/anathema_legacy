package net.sf.anathema.framework.repository.tree;

import net.sf.anathema.lib.file.RelativePath;

public class FileUi {

  public RelativePath getRemoveFilePath() {
    return new RelativePath("icons/ButtonRemoveFile16.png");
  }

  public RelativePath getDuplicateFilePath() {
    return new RelativePath("icons/ButtonDuplicate16.png");
  }

  public RelativePath getExportFilePath() {
    return new RelativePath("icons/ButtonExport16.png");
  }

  public RelativePath getImportFilePath() {
    return new RelativePath("icons/ButtonImport16.png");
  }
}