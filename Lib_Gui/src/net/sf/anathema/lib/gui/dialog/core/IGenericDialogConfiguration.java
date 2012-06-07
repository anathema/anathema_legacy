package net.sf.anathema.lib.gui.dialog.core;

import net.sf.anathema.lib.gui.dialog.userdialog.buttons.IDialogButtonConfiguration;

public interface IGenericDialogConfiguration {

  IDialogButtonConfiguration getButtonConfiguration();

  IDialogHeaderPanelConfiguration getHeaderPanelConfiguration();
}