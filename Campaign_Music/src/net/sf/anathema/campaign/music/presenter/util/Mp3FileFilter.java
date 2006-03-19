package net.sf.anathema.campaign.music.presenter.util;

import net.sf.anathema.lib.io.file.AbstractFileTypeFilter;

public class Mp3FileFilter extends AbstractFileTypeFilter {

  @Override
  protected boolean acceptExtension(String extension) {
    return extension.equals("mp3"); //$NON-NLS-1$
  }
}