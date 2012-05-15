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