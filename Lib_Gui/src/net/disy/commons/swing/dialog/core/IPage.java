/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.disy.commons.swing.dialog.core;

import net.disy.commons.swing.dialog.input.IRequestFinishListener;
import net.disy.commons.swing.dialog.wizard.IWizardConfiguration;

public interface IPage {

  public void addRequestFinishListener(IRequestFinishListener requestFinishListener);

  public void removeRequestFinishListener(IRequestFinishListener requestFinishListener);

  /** Returns this dialog page's description text.
   * @return the description text for this dialog page, or <code>null</code> if none */
  public String getDescription();

  /** Returns whether this page is complete or not.
   * 
   * This information is typically used by the wizard to decide when it is okay to finish.
   * @return <code>true</code> if this page is complete, and <code>false</code> otherwise */
  public boolean canFinish();

  /** Returns this dialog page's title.
   @return the title of this dialog page, or <code>null</code> if none */
  public String getTitle();

  /** Returns a help handler than will be used for handling help requests (e.g. when the user clicks
   * the help button on this page). If there is no help available, return <code>null</code>. In this case 
   * the page container will typicalle disable or remove the help button.
   * Note that for wizards the help button will only by visible if the coresponding wizard
   * configuration returns <code>true</code> in {@link IWizardConfiguration#isHelpAvailable()}.
   *  
   * @return A handler if help is available or <code>null</code> if no help is supported for this page. */
  public IDialogHelpHandler getHelpHandler();

  /** Called from the dialog container when the page is entered. */
  public void enter();

  /** Called from the dialog container when the page is left. */
  public void leave();
}