package net.sf.anathema.lib.gui.dialog;

import net.disy.commons.swing.action.ActionConfiguration;
import net.disy.commons.swing.action.IActionConfiguration;
import net.disy.commons.swing.dialog.userdialog.buttons.DialogButtonConfiguration;
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