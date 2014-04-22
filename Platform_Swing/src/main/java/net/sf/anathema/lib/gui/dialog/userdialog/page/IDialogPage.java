package net.sf.anathema.lib.gui.dialog.userdialog.page;

import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.message.IBasicMessage;

import javax.swing.JComponent;

public interface IDialogPage {

  IBasicMessage createCurrentMessage();

  JComponent createContent();

  IBasicMessage getDefaultMessage();

  void setInputValidListener(ChangeListener listener);

  /**
   * Returns this dialog page's description text.
   *
   * @return the description text for this dialog page, or <code>null</code> if none
   */
  String getDescription();

  /**
   * Returns whether this page is complete or not.
   * <p>
   * This information is typically used by the wizard to decide when it is okay to finish.
   *
   * @return <code>true</code> if this page is complete, and <code>false</code> otherwise
   */
  boolean canFinish();

  /**
   * Returns this dialog page's title.
   *
   * @return the title of this dialog page, or <code>null</code> if none
   */
  String getTitle();
}