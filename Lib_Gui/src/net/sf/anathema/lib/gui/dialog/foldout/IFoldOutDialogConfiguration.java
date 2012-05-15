package net.sf.anathema.lib.gui.dialog.foldout;

import net.sf.anathema.lib.gui.action.IActionConfiguration;
import net.sf.anathema.lib.gui.dialog.userdialog.IDialogConfiguration;
import net.sf.anathema.lib.gui.dialog.userdialog.page.IDialogPage;

public interface IFoldOutDialogConfiguration<P extends IDialogPage> extends IDialogConfiguration<P> {

  IFoldOutPage getFoldOutPage();

  IActionConfiguration getFoldOutButtonConfiguration();

  IActionConfiguration getFoldInButtonConfiguration();

  boolean isInitiallyFoldedOut();
}