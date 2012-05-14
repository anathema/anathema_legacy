/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.disy.commons.swing.dialog.userdialog;

import net.disy.commons.swing.dialog.core.DialogHeaderPanelConfiguration;
import net.disy.commons.swing.dialog.core.IDialogHeaderPanelConfiguration;
import net.disy.commons.swing.dialog.core.IDialogResult;
import net.disy.commons.swing.dialog.core.IVetoDialogCloseHandler;
import net.disy.commons.swing.dialog.core.preferences.IDialogPreferences;
import net.disy.commons.swing.dialog.setting.IDialogVisibilitySetting;
import net.disy.commons.swing.dialog.userdialog.buttons.DialogButtonConfigurationFactory;
import net.disy.commons.swing.dialog.userdialog.buttons.IDialogButtonConfiguration;
import net.disy.commons.swing.dialog.userdialog.page.IDialogPage;

import javax.swing.JComponent;
import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

public class DefaultDialogConfigurationBuilder<P extends IDialogPage> {

  private final P dialogPage;
  private IDialogButtonConfiguration buttonConfiguration = DialogButtonConfigurationFactory
      .createOkCancel();
  private IDialogVisibilitySetting dialogVisibilitySetting;
  private IDialogHeaderPanelConfiguration headerPanelConfiguration = DialogHeaderPanelConfiguration
      .createVisibleWithoutIcon();
  private Dimension customizedPreferedSize = null;
  private IDialogPreferences preferences = null;
  private final List<JComponent> additionalButtons = new ArrayList<JComponent>();
  private IVetoDialogCloseHandler vetoDialogCloseHander = new IVetoDialogCloseHandler() {

    @Override
    public boolean handleDialogAboutToClose(IDialogResult result, Component parentComponent) {
      return true;
    }
  };
  private boolean updateVisibilitySettingOnCancel;

  public DefaultDialogConfigurationBuilder(P dialogPage) {
    this.dialogPage = dialogPage;
  }

  public DefaultDialogConfiguration<P> build() {
    return new DefaultDialogConfiguration<P>(
        dialogPage,
        buttonConfiguration,
        headerPanelConfiguration,
        customizedPreferedSize,
        preferences,
        additionalButtons.toArray(new JComponent[additionalButtons.size()]),
        vetoDialogCloseHander,
        dialogVisibilitySetting,
        updateVisibilitySettingOnCancel);
  }

  public void setButtonConfiguration(IDialogButtonConfiguration buttonConfiguration) {
    this.buttonConfiguration = buttonConfiguration;
  }
}
