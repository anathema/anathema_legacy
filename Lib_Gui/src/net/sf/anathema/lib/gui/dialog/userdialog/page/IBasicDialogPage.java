package net.sf.anathema.lib.gui.dialog.userdialog.page;

import net.sf.anathema.lib.gui.dialog.core.IPage;
import net.sf.anathema.lib.gui.dialog.events.ICheckInputValidListener;
import net.sf.anathema.lib.gui.dialog.userdialog.IDialogConfiguration;
import net.sf.anathema.lib.gui.swing.IDisposable;
import net.sf.anathema.lib.message.IBasicMessage;

import javax.swing.JComponent;

public interface IBasicDialogPage extends IPage, IDisposable {
  IBasicMessage createCurrentMessage();

  void requestFocus();

  JComponent createContent();

  void setInputValidListener(ICheckInputValidListener inputValidListener);

  /** @deprecated As of 11.11.2009 (gebhard), replaced by {@link IDialogConfiguration#getVetoCloseHandler()}
   */
  @Deprecated
  boolean performCancel();

  /** @deprecated As of 11.11.2009 (gebhard), replaced by {@link IDialogConfiguration#getVetoCloseHandler()}
   */
  @Deprecated
  boolean performOk();

  void updateInputValid();
}