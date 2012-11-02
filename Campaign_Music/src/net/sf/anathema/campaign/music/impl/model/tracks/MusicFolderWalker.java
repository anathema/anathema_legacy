package net.sf.anathema.campaign.music.impl.model.tracks;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import net.sf.anathema.campaign.music.model.libary.IMusicFolderWalker;
import net.sf.anathema.campaign.music.model.libary.ITrackHandler;
import net.sf.anathema.campaign.music.model.track.IMp3Track;
import net.sf.anathema.lib.lang.StringUtilities;
import net.sf.anathema.lib.progress.Cancelable;
import net.sf.anathema.lib.progress.IProgressMonitor;
import net.sf.anathema.lib.resources.IResources;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MusicFolderWalker implements IMusicFolderWalker {

  private final File musicFolder;

  public MusicFolderWalker(File musicFolder) throws IOException {
    Preconditions.checkNotNull(musicFolder);
    if (!musicFolder.exists()) {
      throw new IOException("LibraryFile does not exist." + musicFolder); //$NON-NLS-1$
    }
    this.musicFolder = musicFolder;
  }

  public static boolean isMp3File(File file) {
    return !file.isDirectory() && file.getAbsolutePath().endsWith(".mp3"); //$NON-NLS-1$
  }

  @Override
  public List<File> walk(IResources resources, IProgressMonitor monitor, Cancelable cancelFlag,
                         ITrackHandler handler) throws InterruptedException {
    monitor.beginTaskWithUnknownTotalWork(resources.getString(
            "Music.Actions.AddFolder.ProgressMonitor.Preprocessing") + " '" + musicFolder + "'."); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    int trackCount = getTrackCount();
    monitor.beginTask(resources.getString(
            "Music.Actions.AddFolder.ProgressMonitor.Loading") + " '" + musicFolder + "' (" + trackCount + " " + //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
            resources.getString("Music.Actions.AddFolder.ProgressMonitor.Tracks") + //$NON-NLS-1$
            ").", //$NON-NLS-1$
            trackCount);
    List<File> flawedFiles = new ArrayList<>();
    walkFile(monitor, cancelFlag, "", handler, flawedFiles); //$NON-NLS-1$
    return flawedFiles;
  }

  private int getTrackCount() {
    return FileUtilities.getFileCount(musicFolder, true, new Predicate<File>() {
      @Override
      public boolean apply(File file) {
        return isMp3File(file);
      }
    });
  }

  private void walkDirectory(IProgressMonitor monitor, Cancelable cancelFlag, String relativePath,
                             ITrackHandler handler, List<File> flawedFiles) throws InterruptedException {
    File file = new File(musicFolder, relativePath);
    for (File child : file.listFiles()) {
      String childRelativePath = StringUtilities.isNullOrEmpty(
              relativePath) ? child.getName() : relativePath + File.separator + child.getName();
      walkFile(monitor, cancelFlag, childRelativePath, handler, flawedFiles);
    }
  }

  private void walkFile(IProgressMonitor monitor, Cancelable cancelFlag, String relativePath, ITrackHandler handler,
                        List<File> flawedFiles) throws InterruptedException {
    File file = new File(musicFolder, relativePath);
    if (file.isDirectory()) {
      walkDirectory(monitor, cancelFlag, relativePath, handler, flawedFiles);
    }
    if (isMp3File(file)) {
      try {
        IMp3Track mp3Item = new FileMp3Track(file);
        handler.handleMp3(mp3Item);
      } catch (Exception e) {
        flawedFiles.add(file);
      }
      if (cancelFlag.isCanceled()) {
        throw new InterruptedException();
      }
      monitor.worked(1);
    }
  }
}