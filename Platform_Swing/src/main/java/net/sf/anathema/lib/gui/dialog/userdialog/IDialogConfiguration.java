package net.sf.anathema.lib.gui.dialog.userdialog;

import net.sf.anathema.lib.gui.dialog.core.IGenericDialogConfiguration;
import net.sf.anathema.lib.gui.dialog.userdialog.page.IDialogPage;

public interface IDialogConfiguration<P extends IDialogPage> extends IGenericDialogConfiguration {

  P getDialogPage();
}