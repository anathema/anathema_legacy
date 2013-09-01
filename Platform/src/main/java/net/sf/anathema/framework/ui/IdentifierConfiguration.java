package net.sf.anathema.framework.ui;

import net.sf.anathema.lib.gui.AbstractUIConfiguration;
import net.sf.anathema.lib.gui.ConfigurableTooltip;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.lib.util.Identifier;

public class IdentifierConfiguration extends AbstractUIConfiguration<Identifier> {
  private Resources resources;

  public IdentifierConfiguration(Resources resources) {
    this.resources = resources;
  }

  @Override
  protected void configureTooltipForExistingValue(Identifier value, ConfigurableTooltip configurableTooltip) {
    configurableTooltip.appendLine(getLabel(value));
  }

  @Override
  protected String labelForExistingValue(Identifier value) {
    return resources.getString(value.getId());
  }
}