package net.sf.anathema.framework.view.perspective;

import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.gui.icon.ImageProvider;
import net.sf.anathema.lib.resources.Resources;

import javax.swing.Icon;

public class ActionPerspectiveToggle implements PerspectiveToggle {

  private SmartAction action;
  private Resources resources;

  public ActionPerspectiveToggle(SmartAction action, Resources resources) {
    this.action = action;
    this.resources = resources;
  }

  @Override
  public void setIcon(RelativePath relativePath) {
    Icon icon = new ImageProvider().getImageIcon(relativePath);
    action.setIcon(icon);
  }

  @Override
  public void setTooltip(String key) {
    action.setToolTipText(resources.getString(key));
  }

  @Override
  public void setText(String key) {
    action.setName(resources.getString(key));
  }
}
