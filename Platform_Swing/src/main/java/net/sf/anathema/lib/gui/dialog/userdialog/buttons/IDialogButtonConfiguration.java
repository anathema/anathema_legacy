package net.sf.anathema.lib.gui.dialog.userdialog.buttons;

import net.sf.anathema.lib.gui.action.IActionConfiguration;

public interface IDialogButtonConfiguration {

  /** @return Action configuration object for the ok button or <code>null</code> if there shall not be an 
   * ok button available. */
  IActionConfiguration getOkActionConfiguration();

  /** @return Action configuration object for the cancel button or <code>null</code> if there shall not be an 
   * cancel button available. */
  IActionConfiguration getCancelActionConfiguration();
}