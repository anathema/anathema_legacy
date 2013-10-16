package net.sf.anathema.framework.repository.tree;

import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.icon.ImageProvider;

import javax.swing.Icon;

public class FileUi {

  private final ImageProvider imageProvider = new ImageProvider();

  private Icon getIcon(RelativePath path) {
    return imageProvider.getImageIcon(path);
  }

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