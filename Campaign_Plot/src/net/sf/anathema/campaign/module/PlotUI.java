package net.sf.anathema.campaign.module;

import net.sf.anathema.lib.gui.ui.AbstractUI;

import javax.swing.Icon;

public class PlotUI extends AbstractUI {

  public Icon getNoteTabIcon() {
    return getIcon("icons/TabNotes16.png"); //$NON-NLS-1$
  }

  public Icon getSeriesTabIcon() {
    return getIcon("icons/TabSeries16.png"); //$NON-NLS-1$
  }

  public Icon getSeriesIcon() {
    return getIcon("icons/FolderSeries16.png"); //$NON-NLS-1$
  }

  public Icon getStoryIcon() {
    return getIcon("icons/FolderStory16.png"); //$NON-NLS-1$
  }

  public Icon getEpisodeIcon() {
    return getIcon("icons/FolderEpisode16.png"); //$NON-NLS-1$
  }

  public Icon getSceneIcon() {
    return getIcon("icons/FolderScene16.png"); //$NON-NLS-1$
  }

  public Object getUnderlineButtonIcon() {
    return getIcon("icons/underline.gif"); //$NON-NLS-1$
  }
}