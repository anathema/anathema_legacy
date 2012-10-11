package net.sf.anathema.lib.gui.dialog.userdialog.page;

import net.sf.anathema.lib.gui.dialog.core.IPage;
import net.sf.anathema.lib.message.IBasicMessage;

import javax.swing.JComponent;

public interface IDialogPage extends IPage {

  IBasicMessage createCurrentMessage();

  JComponent createContent();

  IBasicMessage getDefaultMessage();
}