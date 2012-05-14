/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.disy.commons.core.model;

import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.exception.UnreachableCodeReachedException;
import org.jmock.example.announcer.Announcer;

public abstract class AbstractChangeableModel implements Cloneable, IChangeableModel {

  private transient Announcer<IChangeListener> listeners = Announcer.to(IChangeListener.class);

  @Override
  protected Object clone() {
    try {
      final AbstractChangeableModel clone = (AbstractChangeableModel) super.clone();
      clone.listeners = Announcer.to(IChangeListener.class);
      return clone;
    }
    catch (final CloneNotSupportedException e) {
      throw new UnreachableCodeReachedException(e);
    }
  }

  @Override
  public void addChangeListener(final IChangeListener listener) {
    listeners.addListener(listener);
  }

  @Override
  public void removeChangeListener(final IChangeListener listener) {
    listeners.removeListener(listener);
  }

  protected void fireChangeEvent() {
    listeners.announce().changeOccurred();
  }

  protected final Object getMutex() {
    return listeners;
  }
}