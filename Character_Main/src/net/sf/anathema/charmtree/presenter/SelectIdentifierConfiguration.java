package net.sf.anathema.charmtree.presenter;

import net.sf.anathema.lib.gui.AbstractUIConfiguration;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.Identifier;

public class SelectIdentifierConfiguration extends AbstractUIConfiguration {
  private Resources resources;

  public SelectIdentifierConfiguration(Resources resources) {
    this.resources = resources;
  }

  @Override
  public String getLabel(Object value) {
    if (value == null) {
      return resources.getString("ComboBox.SelectLabel");
    }
    StringBuilder builder = new StringBuilder();
    builder.append(((Identifier) value).getId());
    return resources.getString(builder.toString());
  }
}