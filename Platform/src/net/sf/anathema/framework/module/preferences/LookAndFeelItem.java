package net.sf.anathema.framework.module.preferences;

import net.sf.anathema.lib.resources.IResources;

public class LookAndFeelItem {

  private final boolean custom;
  private final String name;
  private final String className;

  public LookAndFeelItem(IResources resources) {
    this(resources, null, null);
  }

  public LookAndFeelItem(IResources resources, String name, String className) {
    this.custom = name == null;
    this.name = name != null
            ? name
            : resources.getString("AnathemaCore.Tools.Preferences.CustomLookAndFeel"); //$NON-NLS-1$
    this.className = !"".equals(className) ? className : null;
  }

  public boolean isCustom() {
    return custom;
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