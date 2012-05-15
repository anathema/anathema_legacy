package net.sf.anathema.lib.gui.dialog.core;

import net.sf.anathema.lib.gui.dialog.core.preferences.IDialogPreferences;
import net.sf.anathema.lib.gui.dialog.userdialog.IDialogCloseHandler;
import net.sf.anathema.lib.gui.dialog.userdialog.UserDialog;
import net.sf.anathema.lib.gui.dialog.userdialog.buttons.IDialogButtonConfiguration;
import net.sf.anathema.lib.gui.dialog.wizard.WizardDialog;

public interface IGenericDialogConfiguration {

  IDialogButtonConfiguration getButtonConfiguration();

  IDialogHeaderPanelConfiguration getHeaderPanelConfiguration();

  /** Returns the close handler for this dialog, that will be informed when the dialog
   * is about to close. 
   * The close handler can deny closing the dialog. It also can be used to perform long
   * taking operations e.g. in a progress monitor while the dialog is still open. However it should
   * only be used if it is intended to stay in the dialog if there is a veto (e.g. if a file cannot be
   * saved).
   * Otherwise perform those operations on the {@link IDialogResult} after showing the dialog/wizard
   * via {@link UserDialog#show()} or {@link WizardDialog#show()}, or - for non-modal dialogs - implement
   * a {@link IDialogCloseHandler} for a call to {@link UserDialog#showNonModal(IDialogCloseHandler)}.
   * 
   * @return a close handler and never <code>null</code>.
   */
  IVetoDialogCloseHandler getVetoCloseHandler();

  IDialogPreferences getPreferences();
}