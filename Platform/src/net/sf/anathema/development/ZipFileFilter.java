package net.sf.anathema.development;

import net.sf.anathema.lib.io.file.AbstractFileTypeFilter;

public final class ZipFileFilter extends AbstractFileTypeFilter {

  private static final String ZIP = "zip"; //$NON-NLS-1$

  @Override
  protected boolean acceptExtension(String extension) {
    return ZIP.equalsIgnoreCase(extension);
  }
}