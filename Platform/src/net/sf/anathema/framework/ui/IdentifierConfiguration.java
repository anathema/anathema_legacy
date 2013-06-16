package net.sf.anathema.framework.ui;

import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.Identifier;

public class IdentifierConfiguration implements AgnosticUIConfiguration<Identifier> {
  private Resources resources;

  public IdentifierConfiguration(Resources resources) {
    this.resources = resources;
  }

  @Override
  public RelativePath getIconsRelativePath(Identifier value) {
    return NO_ICON;
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
