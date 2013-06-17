package net.sf.anathema.framework.ui;

import net.sf.anathema.lib.gui.AbstractUIConfiguration;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.Identifier;

public class IdentifierConfiguration extends AbstractUIConfiguration<Identifier> {
  private Resources resources;

  public IdentifierConfiguration(Resources resources) {
    this.resources = resources;
  }

  @Override
  public String getLabel(Identifier value) {
    return resources.getString(value.getId());
  }

  @Override
  public String getToolTipText(Identifier value) {
    return getLabel(value);
  }
}
