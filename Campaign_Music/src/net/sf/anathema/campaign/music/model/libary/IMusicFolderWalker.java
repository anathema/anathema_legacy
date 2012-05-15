package net.sf.anathema.campaign.music.model.libary;

import net.sf.anathema.lib.progress.ICancelable;
import net.sf.anathema.lib.progress.IProgressMonitor;
import net.sf.anathema.lib.resources.IResources;

import java.io.File;
import java.util.List;

public interface IMusicFolderWalker {

  public List<File> walk(IResources resources, IProgressMonitor monitor, ICancelable cancelFlag, ITrackHandler handler)
      throws InterruptedException;
}