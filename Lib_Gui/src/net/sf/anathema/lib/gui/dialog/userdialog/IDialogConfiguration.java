/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.sf.anathema.lib.gui.dialog.userdialog;

import net.sf.anathema.lib.gui.dialog.core.IGenericDialogConfiguration;
import net.sf.anathema.lib.gui.dialog.userdialog.page.IDialogPage;

import javax.swing.JComponent;
import java.awt.Dimension;

public interface IDialogConfiguration<P extends IDialogPage> extends IGenericDialogConfiguration {

  public P getDialogPage();

  public void setUserDialogContainer(IUserDialogContainer userDialog);

  public JComponent[] createAdditionalButtons();

  public JComponent createOptionalButtonPanelLeftComponent();

  public Dimension getCustomizedPreferedSize();

  public boolean isVisible();
}