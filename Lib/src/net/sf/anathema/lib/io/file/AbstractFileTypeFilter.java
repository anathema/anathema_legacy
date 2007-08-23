package net.sf.anathema.lib.io.file;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public abstract class AbstractFileTypeFilter extends FileFilter implements java.io.FileFilter {

  private String getExtension(File file) {
    String fileName = file.getName();
    int lastDotIndex = fileName.lastIndexOf('.');
    if (lastDotIndex < 0) {
      return null;
    }
    if (lastDotIndex == fileName.length() - 1) {
      return ""; //$NON-NLS-1$
    }
    return fileName.substring(lastDotIndex + 1).toLowerCase();
  }

  @Override
  public boolean accept(File f) {
    if (f.isDirectory()) {
      return acceptDirectory(f);
    }
    String extension = getExtension(f);
    if (extension != null) {
      return acceptExtension(extension);
    }
    return false;
  }

  protected abstract boolean acceptExtension(String extension);

  protected boolean acceptDirectory(File directory) {
    return !directory.isHidden();
  }
}