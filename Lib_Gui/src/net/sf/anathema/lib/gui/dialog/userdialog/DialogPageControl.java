/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.sf.anathema.lib.gui.dialog.userdialog;

import net.disy.commons.core.util.ContractFailedException;
import net.disy.commons.core.util.Ensure;
import net.disy.commons.core.util.ObjectUtilities;
import net.disy.commons.swing.events.CheckInputValidListener;
import net.disy.commons.swing.events.IInputValidCheckable;
import net.sf.anathema.lib.gui.dialog.core.DialogPageInputValidCheckable;
import net.sf.anathema.lib.gui.dialog.core.IDialogControl;
import net.sf.anathema.lib.gui.dialog.core.IDialogHelpHandler;
import net.sf.anathema.lib.gui.dialog.userdialog.page.IDialogPage;
import net.sf.anathema.lib.message.IBasicMessage;

import javax.swing.JComponent;

public class DialogPageControl implements IInputValidCheckable, IMessageSetable {
  private IDialogControl dialogControl = new NullDialogControl();
  private JComponent content;
  private final IDialogPage dialogPage;
  private IBasicMessage message;

  public DialogPageControl(final IDialogPage dialogPage) {
    this.dialogPage = dialogPage;
    setMessage(dialogPage.getDefaultMessage());
  }

  public boolean canFinish() {
    return dialogPage.canFinish();
  }

  @Override
  public final void setMessage(final IBasicMessage message) {
    Ensure.ensureArgumentNotNull(message);
    if (ObjectUtilities.equals(this.message, message)) {
      updateCanFinish();
      return;
    }
    this.message = message;
    updateCanFinish();
    dialogControl.updateMessage();
  }

  private void updateCanFinish() {
    dialogControl.updateButtons();
  }

  public final IBasicMessage getMessage() {
    return message;
  }

  public final void setDialogControl(final IDialogControl dialogControl) {
    this.dialogControl = dialogControl;
  }

  public final JComponent getContent() {
    if (content == null) {
      content = dialogPage.createContent();
      if (content == null) {
        throw new ContractFailedException("Method createContent() must not return null in " //$NON-NLS-1$
            + dialogPage.getClass().getName());
      }
      dialogPage.setInputValidListener(new CheckInputValidListener(
          new DialogPageInputValidCheckable(this, dialogPage)));
      updateButtons();
    }
    return content;
  }

  protected void updateButtons() {
    dialogControl.updateButtons();
  }

  /**
   * @deprecated As of 24.2.2005 (gebhard)
   */
  @Deprecated
  public boolean performOk() {
    return dialogPage.performOk();
  }

  /**
   * @deprecated As of 24.2.2005 (gebhard)
   */
  @Deprecated
  public boolean performCancel() {
    return dialogPage.performCancel();
  }

  public String getDescription() {
    return dialogPage.getDescription();
  }

  @Override
  public void checkInputValid() {
    setMessage(dialogPage.createCurrentMessage());
    dialogPage.updateInputValid();
  }

  public String getTitle() {
    return dialogPage.getTitle();
  }

  public void requestFocus() {
    dialogPage.requestFocus();
  }

  public IDialogHelpHandler getHelpHandler() {
    return dialogPage.getHelpHandler();
  }
}