/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.sf.anathema.lib.gui.dialog.userdialog.page;

import net.disy.commons.swing.dispose.IDisposable;
import net.disy.commons.swing.events.ICheckInputValidListener;
import net.sf.anathema.lib.gui.dialog.core.IPage;
import net.sf.anathema.lib.gui.dialog.userdialog.IDialogConfiguration;
import net.sf.anathema.lib.message.IBasicMessage;

import javax.swing.JComponent;

public interface IBasicDialogPage extends IPage, IDisposable {
  public IBasicMessage createCurrentMessage();

  public void requestFocus();

  public JComponent createContent();

  public void setInputValidListener(ICheckInputValidListener inputValidListener);

  /** @deprecated As of 11.11.2009 (gebhard), replaced by {@link IDialogConfiguration#getVetoCloseHandler()}
   */
  @Deprecated
  public boolean performCancel();

  /** @deprecated As of 11.11.2009 (gebhard), replaced by {@link IDialogConfiguration#getVetoCloseHandler()}
   */
  @Deprecated
  public boolean performOk();

  public void updateInputValid();
}