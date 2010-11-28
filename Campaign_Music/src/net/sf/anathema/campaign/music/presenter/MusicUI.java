package net.sf.anathema.campaign.music.presenter;

import javax.swing.Icon;

import net.sf.anathema.lib.resources.AbstractUI;
import net.sf.anathema.lib.resources.IResources;

public class MusicUI extends AbstractUI {

  public MusicUI(IResources resources) {
    super(resources);
  }

  public Icon getSearchIcon() {
    return getIcon("ButtonSearch16.png"); //$NON-NLS-1$
  }

  public Icon getMusicTabIcon() {
    return getIcon("TabMusic16.png"); //$NON-NLS-1$    
  }

  public Icon getMusicToolBarIcon() {
    return getIcon("TaskBarMusic24.png");//$NON-NLS-1$
  }

  public Icon getAddFolderIcon() {
    return getIcon("ButtonAddFolder16.png"); //$NON-NLS-1$
  }

  public Icon getReplaceToLeftIcon() {
    return getIcon("ButtonArrowLeftCross16.png"); //$NON-NLS-1$
  }

  public Icon getPauseButtonIcon() {
    return getIcon("ButtonPause16.png"); //$NON-NLS-1$
  }

  public Icon getResumeButtonIcon() {
    return getIcon("ButtonResume16.png"); //$NON-NLS-1$
  }

  public Icon getPlayButtonIcon() {
    return getIcon("ButtonPlay16.png"); //$NON-NLS-1$
  }

  public Icon getStopButtonIcon() {
    return getIcon("ButtonStop16.png"); //$NON-NLS-1$
  }
}