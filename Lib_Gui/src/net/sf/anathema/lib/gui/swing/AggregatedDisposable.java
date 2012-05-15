/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.sf.anathema.lib.gui.swing;

import java.util.ArrayList;
import java.util.List;


public class AggregatedDisposable implements IDisposable {

  private final List<IDisposable> allDisposables = new ArrayList<IDisposable>();

  @Override
  public void dispose() {
    for (final IDisposable disposable : allDisposables) {
      disposable.dispose();
    }
  }

  public void add(final IDisposable disposable) {
    allDisposables.add(disposable);
  }
}