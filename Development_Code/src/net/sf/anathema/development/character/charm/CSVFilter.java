package net.sf.anathema.development.character.charm;

import net.sf.anathema.lib.io.file.AbstractFileTypeFilter;

public class CSVFilter extends AbstractFileTypeFilter {

  @Override
  protected boolean acceptExtension(String extension) {
    return extension.equals("csv"); //$NON-NLS-1$
  }
}