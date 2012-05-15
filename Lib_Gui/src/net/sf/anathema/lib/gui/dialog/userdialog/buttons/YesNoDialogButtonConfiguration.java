package net.sf.anathema.lib.gui.dialog.userdialog.buttons;

import net.sf.anathema.lib.gui.action.ActionConfiguration;
import net.sf.anathema.lib.gui.action.IActionConfiguration;
import net.sf.anathema.lib.resources.IResources;

public class YesNoDialogButtonConfiguration extends DialogButtonConfiguration {

  private final IResources resources;

  public YesNoDialogButtonConfiguration(IResources resources) {
    this.resources = resources;
  }

  @Override
  public IActionConfiguration getCancelActionConfiguration() {
    return new ActionConfiguration(resources.getString("AnathemaCore.Tools.Question.Cancel")); //$NON-NLS-1$
  }

  @Override
  public IActionConfiguration getOkActionConfiguration() {
    return new ActionConfiguration(resources.getString("AnathemaCore.Tools.Question.Okay")); //$NON-NLS-1$
  }
}