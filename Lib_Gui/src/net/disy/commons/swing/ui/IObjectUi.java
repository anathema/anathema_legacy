/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.disy.commons.swing.ui;

import javax.swing.Icon;

public interface IObjectUi<T> {

  public Icon getIcon(T value);

  public String getLabel(T value);

  /** @return the tooltip text for the combo box or <code>null</code> if none. */
  public String getToolTipText(T value);
}