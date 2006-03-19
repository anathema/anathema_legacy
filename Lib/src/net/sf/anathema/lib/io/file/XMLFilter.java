package net.sf.anathema.lib.io.file;

import java.io.File;

public class XMLFilter extends AbstractFileTypeFilter {

  private final boolean acceptDirectories;

  public XMLFilter() {
    this(true);
  }

  public XMLFilter(boolean acceptDirectories) {
    this.acceptDirectories = acceptDirectories;
  }

  @Override
  protected boolean acceptExtension(String extension) {
    return extension.equals("xml"); //$NON-NLS-1$
  }
  
  @Override
  protected boolean acceptDirectory(File directory) {
    return acceptDirectories && super.acceptDirectory(directory);
  }
}