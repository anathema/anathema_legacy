package net.sf.anathema.framework.view.perspective;

import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.gui.icon.ImageProvider;
import net.sf.anathema.lib.resources.IResources;

import javax.swing.Icon;

public class ActionPerspectiveToggle implements PerspectiveToggle {

  private SmartAction action;
  private IResources resources;
  private Class<?> parent;

  public ActionPerspectiveToggle(SmartAction action, IResources resources, Class<?> parent) {
    this.action = action;
    this.resources = resources;
    this.parent = parent;
  }

  @Override
  public void setIcon(String relativePath) {
    Icon icon = new ImageProvider(".").getImageIcon(parent, relativePath);
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
