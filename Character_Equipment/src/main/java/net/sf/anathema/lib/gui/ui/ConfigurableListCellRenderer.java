package net.sf.anathema.lib.gui.ui;

import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.gui.ConfigurableSwingUI;

public class ConfigurableListCellRenderer extends ObjectUiListCellRenderer {
  public ConfigurableListCellRenderer(AgnosticUIConfiguration configuration) {
    super(new ConfigurableSwingUI(configuration));
  }
}