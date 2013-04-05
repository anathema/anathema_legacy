package net.sf.anathema.framework.presenter.resources;

import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.ui.AbstractUI;

import javax.swing.Icon;

public class FileUi extends AbstractUI {

  public Icon getRemoveFileIcon() {
    return getIcon(new RelativePath("icons/ButtonRemoveFile16.png"));
  }

  public Icon getDuplicateFileIcon() {
    return getIcon(new RelativePath("icons/ButtonDuplicate16.png"));
  }

  public Icon getImportFileIcon() {
    return getIcon(new RelativePath("icons/ButtonImport16.png"));
  }

  public Icon getExportFileIcon() {
    return getIcon(new RelativePath("icons/ButtonExport16.png"));
  }
}