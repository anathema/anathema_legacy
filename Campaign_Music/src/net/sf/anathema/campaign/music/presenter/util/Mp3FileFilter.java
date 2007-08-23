package net.sf.anathema.campaign.music.presenter.util;

import net.sf.anathema.lib.io.file.AbstractFileTypeFilter;
import net.sf.anathema.lib.resources.IResources;

public class Mp3FileFilter extends AbstractFileTypeFilter {

  private final IResources resources;

  public Mp3FileFilter(IResources resources) {
    this.resources = resources;
  }

  @Override
  protected boolean acceptExtension(String extension) {
    return extension.equals("mp3"); //$NON-NLS-1$
  }

  @Override
  public String getDescription() {
    return resources.getString("Filetype.mp3");  //$NON-NLS-1$
  }
}