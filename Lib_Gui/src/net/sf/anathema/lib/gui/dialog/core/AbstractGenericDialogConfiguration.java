/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.sf.anathema.lib.gui.dialog.core;

import com.google.common.base.Preconditions;
import net.sf.anathema.lib.gui.dialog.core.preferences.IDialogPreferences;
import net.sf.anathema.lib.gui.dialog.userdialog.buttons.IDialogButtonConfiguration;

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
    Preconditions.checkNotNull(buttonConfiguration);
    Preconditions.checkNotNull(headerPanelConfiguration);
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