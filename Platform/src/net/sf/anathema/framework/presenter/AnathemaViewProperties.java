package net.sf.anathema.framework.presenter;

import net.sf.anathema.lib.gui.icon.ImageProvider;
import net.sf.anathema.lib.resources.Resources;

import java.awt.Frame;
import java.awt.Image;

public class AnathemaViewProperties {

  private final Resources resources;
  private final boolean maximized;

  public AnathemaViewProperties(Resources resources, boolean maximized) {
    this.resources = resources;
    this.maximized = maximized;
  }

  public String getDefaultFrameTitle() {
    return resources.getString("MainFrame.Title"); //$NON-NLS-1$;
  }

  public Image getFrameIcon() {
    return new ImageProvider(".").getImage(this.getClass(), "icons/AnathemaIcon.png"); //$NON-NLS-1$
  }

  public String getMainMenuName() {
    return resources.getString("AnathemaCore.MainMenu.Name"); //$NON-NLS-1$
  }

  public int getLaunchState() {
    return maximized ? Frame.MAXIMIZED_BOTH : Frame.NORMAL;
  }

  public String getHelpMenuName() {
    return resources.getString("AnathemaCore.HelpMenu.Name"); //$NON-NLS-1$;
  }
}