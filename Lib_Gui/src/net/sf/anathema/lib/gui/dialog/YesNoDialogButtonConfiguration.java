package net.sf.anathema.lib.gui.dialog;

import net.disy.commons.swing.dialog.userdialog.buttons.AbstractDialogButtonConfiguration;
import net.sf.anathema.lib.resources.IResources;

public class YesNoDialogButtonConfiguration extends AbstractDialogButtonConfiguration {

  private final IResources resources;

  public YesNoDialogButtonConfiguration(IResources resources) {
    this.resources = resources;
  }

  @Override
  public String getCancelButtonText() {
    return resources.getString("AnathemaCore.Tools.Question.Cancel"); //$NON-NLS-1$
  }

  @Override
  public String getOkayButtonText() {
    return resources.getString("AnathemaCore.Tools.Question.Okay"); //$NON-NLS-1$
  }
}