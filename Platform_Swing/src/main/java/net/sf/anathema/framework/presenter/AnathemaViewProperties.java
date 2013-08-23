package net.sf.anathema.framework.presenter;

import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.resources.Resources;

import java.awt.Frame;

public class AnathemaViewProperties {

  private final Resources resources;
  private final boolean maximized;

  public AnathemaViewProperties(Resources resources, boolean maximized) {
    this.resources = resources;
    this.maximized = maximized;
  }

  public String getDefaultFrameTitle() {
    return resources.getString("MainFrame.Title");
  }

  public RelativePath getFrameIcon() {
    return new RelativePath("icons/AnathemaIcon.png");
  }

  public String getMainMenuName() {
    return resources.getString("AnathemaCore.MainMenu.Name");
  }

  public int getLaunchState() {
    return maximized ? Frame.MAXIMIZED_BOTH : Frame.NORMAL;
  }

  public String getHelpMenuName() {
    return resources.getString("AnathemaCore.HelpMenu.Name");
  }
}