/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.disy.commons.swing.dialog.core.internal;

import net.disy.commons.core.util.Ensure;
import net.disy.commons.swing.dialog.core.IDialogHeaderPanelConfiguration;
import net.disy.commons.swing.dialog.core.IGenericDialogConfiguration;
import net.disy.commons.swing.dialog.core.preferences.IDialogPreferences;
import net.disy.commons.swing.dialog.userdialog.buttons.IDialogButtonConfiguration;

public abstract class AbstractGenericDialogConfiguration implements IGenericDialogConfiguration {

  private final IDialogButtonConfiguration buttonConfiguration;
  private final IDialogPreferences preference;
  private final IDialogHeaderPanelConfiguration headerPanelConfiguration;

  public AbstractGenericDialogConfiguration(
      final IDialogButtonConfiguration buttonConfiguration,
      IDialogHeaderPanelConfiguration headerPanelConfiguration) {
    this(buttonConfiguration, headerPanelConfiguration, null);
  }

  public AbstractGenericDialogConfiguration(
      final IDialogButtonConfiguration buttonConfiguration,
      IDialogHeaderPanelConfiguration headerPanelConfiguration,
      final IDialogPreferences preference) {
    Ensure.ensureArgumentNotNull(buttonConfiguration);
    Ensure.ensureArgumentNotNull(headerPanelConfiguration);
    this.headerPanelConfiguration = headerPanelConfiguration;
    this.buttonConfiguration = buttonConfiguration;
    this.preference = preference;
  }

  @Override
  public final IDialogButtonConfiguration getButtonConfiguration() {
    return buttonConfiguration;
  }

  @Override
  public IDialogPreferences getPreferences() {
    return preference;
  }

  @Override
  public IDialogHeaderPanelConfiguration getHeaderPanelConfiguration() {
    return headerPanelConfiguration;
  }
}