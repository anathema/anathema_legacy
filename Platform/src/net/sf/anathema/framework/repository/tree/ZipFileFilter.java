package net.sf.anathema.framework.repository.tree;

import net.sf.anathema.lib.io.file.AbstractFileTypeFilter;
import net.sf.anathema.lib.resources.IResources;

public final class ZipFileFilter extends AbstractFileTypeFilter {

  private static final String ZIP = "zip"; //$NON-NLS-1$
  private final IResources resources;

  public ZipFileFilter(IResources resources) {
    this.resources = resources;
  }

  @Override
  protected boolean acceptExtension(String extension) {
    return ZIP.equalsIgnoreCase(extension);
  }

  @Override
  public String getDescription() {
    return resources.getString("Filetype.zip"); //$NON-NLS-1$
  }
}