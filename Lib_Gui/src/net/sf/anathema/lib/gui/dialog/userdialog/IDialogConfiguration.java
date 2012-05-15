package net.sf.anathema.lib.gui.dialog.userdialog;

import net.sf.anathema.lib.gui.dialog.core.IGenericDialogConfiguration;
import net.sf.anathema.lib.gui.dialog.userdialog.page.IDialogPage;

import javax.swing.JComponent;
import java.awt.Dimension;

public interface IDialogConfiguration<P extends IDialogPage> extends IGenericDialogConfiguration {

  P getDialogPage();

  void setUserDialogContainer(IUserDialogContainer userDialog);

  JComponent[] createAdditionalButtons();

  JComponent createOptionalButtonPanelLeftComponent();

  Dimension getCustomizedPreferedSize();

  boolean isVisible();
}