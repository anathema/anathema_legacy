/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.disy.commons.swing.dialog.core;

public interface IDialogControl {

  /**
   * Adjusts the enable state of the Back, Next, and Finish buttons to reflect the state of the
   * currently active page in this container.
   * This method is called by the container itself when its contents changes and may be called by
   * by others at any time to force a button state update. */
  public void updateButtons();

  /**
   * Updates the message (or error message) shown in the message line to reflect the state of the
   * currently active page in this container.
   * This method is called by the container itself when its contents changes and may be called
   * by others at any time to force a message update.
   */
  public void updateMessage();

  /**
   * Updates the title bar (title, description, and image) to reflect the state of the currently
   * active page in this container.
   * This method is called by the container itself when its wizard page changes and may be called
   * by others at any time to force a title bar update.
   */
  public void updateDescription();

  /** Updates the window title to reflect the current state.
   * This method is called by the container itself when its contents changes (in wizards for
   * example) and may be called by by others at any time to force a window title change.
   */
  public void updateTitle();
}