/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.sf.anathema.lib.gui.dialog.progress;

import com.google.common.base.Preconditions;
import net.disy.commons.core.progress.ICanceledListener;
import net.disy.commons.core.progress.IProgressMonitor;

import javax.swing.JProgressBar;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class ProgressMonitorBar extends JProgressBar implements IProgressMonitor {
  private static final int EXPIRATION_MILLIS = 5000;

  private final Timer expirationTimer = new Timer(EXPIRATION_MILLIS, new ActionListener() {
    @Override
    public void actionPerformed(final ActionEvent e) {
      setIndeterminate(active);
    }
  });

  private boolean unknownTotalWork;
  private final Collection<ICanceledListener> canceledListeners = new ArrayList<ICanceledListener>();
  private boolean canceled = false;
  private boolean active = false;

  /**
   * Creates a TimeSlider and register ist as a GisTermService
   */
  public ProgressMonitorBar() {
    setOpaque(true);
    setDoubleBuffered(false);
    setBorderPainted(false);
    setStringPainted(false);
  }

  @Override
  public final void beginTaskWithUnknownTotalWork(final String name) {
    beginTask(name, UNKNOWN);
  }

  @Override
  public void beginTask(final String name, final int totalWork) {
    canceled = false;
    setMinimum(0);

    unknownTotalWork = (totalWork == UNKNOWN);
    setIndeterminate(unknownTotalWork);
    setStringPainted(!unknownTotalWork);
    if (!unknownTotalWork) {
      super.setMaximum(totalWork);
    }
    actuallySetValue(0, true);
  }

  @Override
  public void done() {
    setStringPainted(false);
    actuallySetValue(0, false);
    setIndeterminate(false);
  }

  public boolean isCanceled() {
    return canceled;
  }

  @Override
  public void setCanceled(final boolean canceled) {
    this.canceled = canceled;
    if (canceled) {
      fireCanceled();
    }
  }

  @Override
  public void subTask(final String name) {
    //nothing to do
  }

  @Override
  public void worked(final int work) {
    if (!unknownTotalWork) {
      actuallySetValue(getValue() + work, true);
    }
  }

  private void actuallySetValue(final int newValue, final boolean newActive) {
    this.active = newActive;
    setValue(newValue);
    expirationTimer.restart();
    setIndeterminate(unknownTotalWork);
  }

  public void finish() {
    setIndeterminate(false);
    actuallySetValue(getMaximum(), false);
  }

  public synchronized void addCanceledListener(final ICanceledListener listener) {
    Preconditions.checkNotNull(listener);
    canceledListeners.add(listener);
  }

  public synchronized void removeCanceledListener(final ICanceledListener listener) {
    canceledListeners.remove(listener);
  }

  private void fireCanceled() {
    final List<ICanceledListener> clonedListeners;
    synchronized (this) {
      clonedListeners = new ArrayList<ICanceledListener>(canceledListeners);
    }
    for (final Iterator<ICanceledListener> iter = clonedListeners.iterator(); iter.hasNext();) {
      final ICanceledListener listener = iter.next();
      listener.canceled();
    }
  }
}