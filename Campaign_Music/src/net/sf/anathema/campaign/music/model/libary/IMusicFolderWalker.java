package net.sf.anathema.campaign.music.model.libary;

import net.sf.anathema.lib.progress.Cancelable;
import net.sf.anathema.lib.progress.IProgressMonitor;
import net.sf.anathema.lib.resources.IResources;

public interface IMusicFolderWalker {

  void walk(IResources resources, IProgressMonitor monitor, Cancelable cancelFlag, ITrackHandler handler)
      throws InterruptedException;
}