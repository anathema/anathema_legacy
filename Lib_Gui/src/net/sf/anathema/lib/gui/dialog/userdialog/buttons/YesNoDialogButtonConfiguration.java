package net.sf.anathema.lib.gui.dialog.userdialog.buttons;

import net.sf.anathema.lib.gui.action.ActionConfiguration;
import net.sf.anathema.lib.gui.action.IActionConfiguration;
import net.sf.anathema.lib.resources.Resources;

public class YesNoDialogButtonConfiguration extends DialogButtonConfiguration {

  private final Resources resources;

  public YesNoDialogButtonConfiguration(Resources resources) {
    this.resources = resources;
  }

  @Override
  public IActionConfiguration getCancelActionConfiguration() {
    return new ActionConfiguration(resources.getString("AnathemaCore.Tools.Question.Cancel"));
  }

  @Override
  public IActionConfiguration getOkActionConfiguration() {
    return new ActionConfiguration(resources.getString("AnathemaCore.Tools.Question.Okay"));
  }
}