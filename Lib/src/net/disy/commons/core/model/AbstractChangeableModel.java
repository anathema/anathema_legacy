/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.disy.commons.core.model;

import net.disy.commons.core.model.listener.IChangeListener;
import net.disy.commons.core.model.listener.ListenerList;
import net.disy.commons.core.model.listener.NotifyChangeListenerClosure;
import net.sf.anathema.lib.exception.UnreachableCodeReachedException;

public abstract class AbstractChangeableModel implements Cloneable, IChangeableModel {

  private transient ListenerList<IChangeListener> listeners = new ListenerList<IChangeListener>();

  @Override
  protected Object clone() {
    try {
      final AbstractChangeableModel clone = (AbstractChangeableModel) super.clone();
      clone.listeners = new ListenerList<IChangeListener>();
      return clone;
    }
    catch (final CloneNotSupportedException e) {
      throw new UnreachableCodeReachedException(e);
    }
  }

  @Override
  public void addChangeListener(final IChangeListener listener) {
    listeners.add(listener);
  }

  @Override
  public void removeChangeListener(final IChangeListener listener) {
    listeners.remove(listener);
  }

  protected void fireChangeEvent() {
    listeners.forAllDo(NotifyChangeListenerClosure.INSTANCE);
  }

  protected final Object getMutex() {
    return listeners;
  }
}