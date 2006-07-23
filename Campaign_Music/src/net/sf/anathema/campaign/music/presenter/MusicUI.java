package net.sf.anathema.campaign.music.presenter;

import javax.swing.Icon;

import net.sf.anathema.lib.resources.AbstractUI;
import net.sf.anathema.lib.resources.IResources;

public class MusicUI extends AbstractUI {

  public MusicUI(IResources resources) {
    super(resources);
  }

  public Icon getMusicTabIcon() {
    return getIcon("TabMusic16.png"); //$NON-NLS-1$    
  }

  public Icon getMusicToolBarIcon() {
    return getIcon("TaskBarMusic24.png");//$NON-NLS-1$
  }

  public Icon getAddFileIcon() {
    return getIcon("MP3FileIcon20.png"); //$NON-NLS-1$
  }

  public Icon getAddFolderIcon() {
    return getIcon("Folder20.gif"); //$NON-NLS-1$
  }

  public Icon getExportPlaylistIcon() {
    return getIcon("tools/Save24.gif"); //$NON-NLS-1$
  }

  public Icon getReplaceToLeftIcon() {
    return getIcon("GreenArrowLeftWithRedX20.png"); //$NON-NLS-1$
  }

  public Icon getPauseButtonIcon() {
    return getIcon("PauseButton20.png"); //$NON-NLS-1$
  }

  public Icon getResumeButtonIcon() {
    return getIcon("ResumeButton20.png"); //$NON-NLS-1$
  }

  public Icon getPlayButtonIcon() {
    return getIcon("PlayButton20.png"); //$NON-NLS-1$
  }

  public Icon getStopButtonIcon() {
    return getIcon("StopButton20.png"); //$NON-NLS-1$
  }
}