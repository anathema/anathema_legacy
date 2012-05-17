package net.sf.anathema.lib.gui.dialog.userdialog.page;

import net.sf.anathema.lib.gui.dialog.core.IPage;
import net.sf.anathema.lib.gui.dialog.events.ICheckInputValidListener;
import net.sf.anathema.lib.gui.swing.IDisposable;
import net.sf.anathema.lib.message.IBasicMessage;

import javax.swing.JComponent;

public interface IDialogPage extends IPage, IDisposable {

  IBasicMessage createCurrentMessage();

  void requestFocus();

  JComponent createContent();

  void setInputValidListener(ICheckInputValidListener inputValidListener);

  void updateInputValid();

  IBasicMessage getDefaultMessage();
}