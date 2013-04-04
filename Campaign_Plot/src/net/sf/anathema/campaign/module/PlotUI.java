package net.sf.anathema.campaign.module;

import net.sf.anathema.lib.gui.ui.AbstractUI;

import javax.swing.Icon;

public class PlotUI extends AbstractUI {

  public Icon getNoteTabIcon() {
    return getIcon("icons/TabNotes16.png");
  }

  public Icon getSeriesTabIcon() {
    return getIcon("icons/TabSeries16.png");
  }

  public Icon getSeriesIcon() {
    return getIcon("icons/FolderSeries16.png");
  }

  public Icon getStoryIcon() {
    return getIcon("icons/FolderStory16.png");
  }

  public Icon getEpisodeIcon() {
    return getIcon("icons/FolderEpisode16.png");
  }

  public Icon getSceneIcon() {
    return getIcon("icons/FolderScene16.png");
  }

  public Object getUnderlineButtonIcon() {
    return getIcon("icons/underline.gif");
  }
}