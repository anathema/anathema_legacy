/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.disy.commons.swing.dialog.foldout;

import javax.swing.JComponent;

public abstract class AbstractFoldOutPage implements IFoldOutPage {
  private JComponent content;

  @Override
  public JComponent getContent() {
    if (content == null) {
      content = createContent();
    }
    return content;
  }

  protected abstract JComponent createContent();

  @Override
  public void dispose() {
    // nothing to do
  }
}