/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.disy.commons.swing.dialog.wizard;

import net.disy.commons.swing.dialog.core.IPage;
import net.disy.commons.swing.dialog.core.IPageContent;
import net.sf.anathema.lib.message.IBasicMessage;

/**
 * Interface for a wizard page.
 * 
 *  The class {@link AbstractWizardPage} provides an abstract implementation of this interface. However,
 *  clients are also free to implement this interface if WizardPage does not suit their needs.
 */
public interface IWizardPage extends IPage {

  /**
   * Returns the current message for this dialog page. A message provides either instruction or
   * information to the user, or an error description which should describe some error state.
   * 
   * @return the message, or <code>null</code> if none
   */
  public IBasicMessage getMessage();

  public IPageContent getPageContent();

  /**
   * Returns whether the next page could be displayed.
   * 
   * @return <code>true</code> if the next page could be displayed, and <code>false</code> otherwise
   */
  public boolean canFlipToNextPage();

  /** Returns the wizard page that would to be shown if the user was to press the Next button. */
  public IWizardPage getNextPage();

  /** Returns the wizard page that would to be shown if the user was to press the Back button. */
  public IWizardPage getPreviousPage();

  /**
   * Returns whether this page is cancellable or not. This information is typically used by the
   * wizard to decide if the current page is cancellable.
   * 
   * @return <code>true</code> if this page is cancellable, and <code>false</code> otherwise
   */
  public boolean canCancel();

  /** Returns the wizard that hosts this wizard page.
   * @deprecated As of 29.05.2009 (gebhard) - use listeners or other ways to retrieve the required
   * information
   */
  @Deprecated
  public IWizardConfiguration getWizard();

  /**
   * Sets the current message for this page. May be <code>null</code> to indicate no
   * message. An error message should describe some error state, as opposed to an information
   * message which may simply provide instruction or information to the user.
   * @param message the message
   */
  public void setMessage(IBasicMessage message);

  /** Initializes all widgets from the data attached to this wizard page. This method will be called
   * from the wizard container after creating the content.
   * @deprecated As of 10.12.2004 (gebhard)
   * @see #getContent() */
  @Deprecated
  public void initializeFromData();

}