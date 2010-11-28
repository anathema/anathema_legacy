package net.sf.anathema.development.properties;

import net.sf.anathema.lib.io.file.AbstractFileTypeFilter;

public class PropertiesFilter extends AbstractFileTypeFilter {

  @Override
  protected boolean acceptExtension(String extension) {
    return extension.equals("properties"); //$NON-NLS-1$
  }

  @Override
  public String getDescription() {
    return "Property files (*.properties)";
  }
}