package net.sf.anathema.framework.repository.tree;

import net.sf.anathema.lib.io.AbstractFileTypeFilter;
import net.sf.anathema.lib.resources.Resources;

public class ZipFileFilter extends AbstractFileTypeFilter {

  private static final String ZIP = "zip";
  private final Resources resources;

  public ZipFileFilter(Resources resources) {
    this.resources = resources;
  }

  @Override
  protected boolean acceptExtension(String extension) {
    return ZIP.equalsIgnoreCase(extension);
  }

  @Override
  public String getDescription() {
    return resources.getString("Filetype.zip");
  }
}