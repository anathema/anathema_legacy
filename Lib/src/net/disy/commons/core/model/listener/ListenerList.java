/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.disy.commons.core.model.listener;

import net.disy.commons.core.util.Ensure;
import net.disy.commons.core.util.IExceptionThrowingClosure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ListenerList<L> {
  private final List<L> listeners;

  public ListenerList() {
    this(new ArrayList<L>());
  }

  private ListenerList(final List<L> listeners) {
    Ensure.ensureArgumentNotNull(listeners);
    this.listeners = listeners;
  }

  public synchronized void add(final L listener) {
    Ensure.ensureArgumentNotNull(listener);
    listeners.add(listener);
  }

  public synchronized void remove(final L listener) {
    listeners.remove(listener);
  }

  public <E extends Throwable> void forAllDo(final IExceptionThrowingClosure<L, E> closure)
      throws E {
    final Collection<L> cloneList;
    synchronized (this) {
      cloneList = new ArrayList<L>(listeners);
    }
    for (L listener : cloneList) {
      closure.execute(listener);
    }
  }

  @Override
  public String toString() {
    return "ListenerList: " + listeners.toString(); //$NON-NLS-1$
  }
}