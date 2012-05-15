package net.sf.anathema.lib.gui.action;

import net.sf.anathema.lib.util.ObjectUtilities;

import javax.swing.Icon;

public class ActionConfiguration implements IActionConfiguration {

  private final String name;
  private final Icon icon;
  private final String toolTipText;

  public ActionConfiguration() {
    this(null, null, null);
  }

  public ActionConfiguration(String name) {
    this(name, null, null);
  }

  public ActionConfiguration(String name, Icon icon) {
    this(name, icon, null);
  }

  public ActionConfiguration(String name, Icon icon, String toolTipText) {
    this.name = name;
    this.icon = icon;
    this.toolTipText = toolTipText;
  }

  @Override
  public Icon getIcon() {
    return icon;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String getToolTipText() {
    return toolTipText;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof ActionConfiguration)) {
      return false;
    }
    ActionConfiguration other = (ActionConfiguration) obj;
    return ObjectUtilities.equals(other.name, name)
        && ObjectUtilities.equals(other.icon, icon)
        && ObjectUtilities.equals(toolTipText, other.toolTipText);
  }

  @Override
  public int hashCode() {
    return name.hashCode();
  }
}