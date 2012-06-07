package net.sf.anathema.lib.gui.dialog.wizard;

import net.sf.anathema.lib.gui.dialog.core.IPage;
import net.sf.anathema.lib.gui.dialog.core.IPageContent;
import net.sf.anathema.lib.message.IBasicMessage;

public interface IWizardPage extends IPage {

  /**
   * Returns the current message for this dialog page. A message provides either instruction or
   * information to the user, or an error description which should describe some error state.
   * 
   * @return the message, or <code>null</code> if none
   */
  IBasicMessage getMessage();

  IPageContent getPageContent();

  /**
   * Returns whether the next page could be displayed.
   * 
   * @return <code>true</code> if the next page could be displayed, and <code>false</code> otherwise
   */
  boolean canFlipToNextPage();

  /** Returns the wizard page that would to be shown if the user was to press the Next button. */
  IWizardPage getNextPage();

  /** Returns the wizard page that would to be shown if the user was to press the Back button. */
  IWizardPage getPreviousPage();

  /**
   * Returns whether this page is cancellable or not. This information is typically used by the
   * wizard to decide if the current page is cancellable.
   * 
   * @return <code>true</code> if this page is cancellable, and <code>false</code> otherwise
   */
  boolean canCancel();

  /**
   * Sets the current message for this page. May be <code>null</code> to indicate no
   * message. An error message should describe some error state, as opposed to an information
   * message which may simply provide instruction or information to the user.
   * @param message the message
   */
  void setMessage(IBasicMessage message);
}