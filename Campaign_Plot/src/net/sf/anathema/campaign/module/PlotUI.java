package net.sf.anathema.campaign.module;

import javax.swing.Icon;

import net.sf.anathema.lib.resources.AbstractUI;
import net.sf.anathema.lib.resources.IResources;

public class PlotUI extends AbstractUI {

  public PlotUI(IResources resources) {
    super(resources);
  }

  public Icon getNoteTabIcon() {
    return getIcon("TabNotes16.png"); //$NON-NLS-1$
  }

  public Icon getSeriesTabIcon() {
    return getIcon("TabSeries16.png"); //$NON-NLS-1$
  }

  public Icon getSeriesIcon() {
    return getIcon("FolderSeries16.png"); //$NON-NLS-1$
  }

  public Icon getStoryIcon() {
    return getIcon("FolderStory16.png"); //$NON-NLS-1$
  }

  public Icon getEpisodeIcon() {
    return getIcon("FolderEpisode16.png"); //$NON-NLS-1$
  }

  public Icon getSceneIcon() {
    return getIcon("FolderScene16.png"); //$NON-NLS-1$
  }

  public Object getUnderlineButtonIcon() {
    return getIcon("underline.gif"); //$NON-NLS-1$
  }
}