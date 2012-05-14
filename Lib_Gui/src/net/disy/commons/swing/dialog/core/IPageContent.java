/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.disy.commons.swing.dialog.core;

import net.disy.commons.swing.component.IDisposableComponentContainer;

public interface IPageContent extends IDisposableComponentContainer {

  /** Set the focus to the first input control. Called by the dialog before showing this page the
   * first time. Usually the page calls the <code>requestFocus()</code> on its first input widget.*/
  public void requestFocus();
}