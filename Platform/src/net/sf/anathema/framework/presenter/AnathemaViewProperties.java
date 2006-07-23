package net.sf.anathema.framework.presenter;

import java.awt.Frame;
import java.awt.Image;
import java.util.Random;

import javax.swing.Icon;

import net.sf.anathema.lib.resources.IResources;

public class AnathemaViewProperties {

  private final IResources resources;
  private final boolean maximized;

  public AnathemaViewProperties(IResources resources, boolean maximized) {
    this.resources = resources;
    this.maximized = maximized;
  }

  public Icon getSplashImage() {
    int random = new Random().nextInt(5);
    return resources.getImageIcon("core/AnathemaSplash" + (random + 3) + ".png"); //$NON-NLS-1$ //$NON-NLS-2$
  }

  public String getDefaultFrameTitle() {
    return resources.getString("MainFrame.Title"); //$NON-NLS-1$;
  }

  @SuppressWarnings("deprecation")
  public Image getFrameIcon() {
    return resources.getImage("core/AnathemaIcon.png"); //$NON-NLS-1$
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