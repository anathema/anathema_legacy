/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.disy.commons.swing.dialog.core;

import net.disy.commons.swing.dialog.core.preferences.IDialogPreferences;
import net.disy.commons.swing.dialog.userdialog.IDialogCloseHandler;
import net.disy.commons.swing.dialog.userdialog.UserDialog;
import net.disy.commons.swing.dialog.userdialog.buttons.IDialogButtonConfiguration;
import net.disy.commons.swing.dialog.wizard.WizardDialog;

public interface IGenericDialogConfiguration {

  public IDialogButtonConfiguration getButtonConfiguration();

  public IDialogHeaderPanelConfiguration getHeaderPanelConfiguration();

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
  public IVetoDialogCloseHandler getVetoCloseHandler();

  public IDialogPreferences getPreferences();
}