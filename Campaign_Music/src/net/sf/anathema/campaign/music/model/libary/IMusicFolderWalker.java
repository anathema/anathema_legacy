package net.sf.anathema.campaign.music.model.libary;

import java.io.File;
import java.util.List;

import net.disy.commons.core.progress.ICancelable;
import net.disy.commons.core.progress.IProgressMonitor;
import net.sf.anathema.lib.resources.IResources;

public interface IMusicFolderWalker {

  public List<File> walk(IResources resources, IProgressMonitor monitor, ICancelable cancelFlag, ITrackHandler handler)
      throws InterruptedException;
}