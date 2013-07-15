package net.sf.anathema.framework.module.preferences;

import net.sf.anathema.lib.resources.Resources;

public class LookAndFeelItem {

  private final String name;
  private final String className;

  public LookAndFeelItem(Resources resources, String name, String className) {
    this.name = name != null
            ? name
            : resources.getString("AnathemaCore.Tools.Preferences.CustomLookAndFeel");
    this.className = !"".equals(className) ? className : null;
  }

  public String getName() {
    return name;
  }

  public String getClassName() {
    return className;
  }

  public String toString() {
    return getName();
  }
}