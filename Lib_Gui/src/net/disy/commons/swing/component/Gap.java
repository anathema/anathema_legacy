/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.disy.commons.swing.component;

import javax.swing.JComponent;
import java.awt.Dimension;

public class Gap extends JComponent {

  private Dimension preferredSize;

  public Gap(int preferredWidth, int preferredHeight) {
    this(new Dimension(preferredWidth, preferredHeight));
  }

  public Gap(Dimension preferredSize) {
    super();
    this.preferredSize = preferredSize;
  }

  @Override
  public Dimension getPreferredSize() {
    if (preferredSize == null) {
      return super.getPreferredSize();
    }
    return preferredSize;
  }
}