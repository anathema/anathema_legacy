package net.sf.anathema.lib.gui.dialog.userdialog.page;

import net.sf.anathema.lib.message.IBasicMessage;

public interface IDialogPage extends IBasicDialogPage {

  IBasicMessage getDefaultMessage();
}