/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.disy.commons.swing.dialog.userdialog.page;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.lib.message.BasicMessage;
import net.sf.anathema.lib.message.IBasicMessage;

public abstract class AbstractDialogPage extends AbstractBasicDialogPage implements IDialogPage {
  private final IBasicMessage defaultMessage;

  public AbstractDialogPage(final String defaultMessageText) {
    Ensure.ensureArgumentNotNull("DefaultMessage text must not be null.", defaultMessageText); //$NON-NLS-1$
    this.defaultMessage = new BasicMessage(defaultMessageText);
  }

  @Override
  public final IBasicMessage getDefaultMessage() {
    return defaultMessage;
  }

  /**
   * @return a non-null value of a message representing the current dialog state. This should be an error message
   * when the dialog content is invalid. Simply return {@link #getDefaultMessage()} if there is no error or warning
   * state.
   */
  @Override
  public abstract IBasicMessage createCurrentMessage();

  @Override
  public boolean canFinish() {
    return !createCurrentMessage().isErrorMessage();
  }
}