/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.disy.commons.core.model;

import net.disy.commons.core.model.listener.ListenerList;
import net.disy.commons.core.model.listener.NotifyChangeListenerClosure;
import net.disy.commons.core.util.ObjectUtilities;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.exception.UnreachableCodeReachedException;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public abstract class SmartChangeableModel implements Cloneable, IChangeableModel {

  private transient ListenerList<IChangeListener> listeners = new ListenerList<IChangeListener>();
  private transient ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

  @Override
  protected Object clone() {
    try {
      final SmartChangeableModel clone = (SmartChangeableModel) super.clone();
      clone.listeners = new ListenerList<IChangeListener>();
      clone.readWriteLock = new ReentrantReadWriteLock();
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

  protected <T> T getValue(final IProperty<T> property) {
    final Lock readLock = readWriteLock.readLock();
    readLock.lock();
    try {
      return property.getValue();
    }
    finally {
      readLock.unlock();
    }
  }

  protected final <T> void setValue(final IProperty<T> property, final T value) {
    final Lock writeLock = readWriteLock.writeLock();
    writeLock.lock();
    try {
      if (ObjectUtilities.equals(property.getValue(), value)) {
        return;
      }
      property.setValue(value);
    }
    finally {
      writeLock.unlock();
    }
    fireChangeEvent();
  }

}