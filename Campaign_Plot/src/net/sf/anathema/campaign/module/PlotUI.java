package net.sf.anathema.campaign.module;

import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.ui.AbstractUI;

import javax.swing.Icon;

public class PlotUI extends AbstractUI {

  public Icon getNoteTabIcon() {
    return getIcon(new RelativePath("icons/TabNotes16.png"));
  }

  public Icon getSeriesTabIcon() {
    return getIcon(new RelativePath("icons/TabSeries16.png"));
  }

  public Icon getSeriesIcon() {
    return getIcon(new RelativePath("icons/FolderSeries16.png"));
  }

  public Icon getStoryIcon() {
    return getIcon(new RelativePath("icons/FolderStory16.png"));
  }

  public Icon getEpisodeIcon() {
    return getIcon(new RelativePath("icons/FolderEpisode16.png"));
  }

  public Icon getSceneIcon() {
    return getIcon(new RelativePath("icons/FolderScene16.png"));
  }

  public Object getUnderlineButtonIcon() {
    return getIcon(new RelativePath("icons/underline.gif"));
  }
}