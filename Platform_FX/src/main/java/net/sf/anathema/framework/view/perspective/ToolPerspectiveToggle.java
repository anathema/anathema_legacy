package net.sf.anathema.framework.view.perspective;

import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.resources.Resources;

public class ToolPerspectiveToggle implements PerspectiveToggle {

  private Tool action;
  private Resources resources;

  public ToolPerspectiveToggle(Tool action, Resources resources) {
    this.action = action;
    this.resources = resources;
  }

  @Override
  public void setIcon(RelativePath relativePath) {
    action.setIcon(relativePath);
  }

  @Override
  public void setTooltip(String key) {
    action.setTooltip(resources.getString(key));
  }

  @Override
  public void setText(String key) {
    action.setText(resources.getString(key));
  }
}