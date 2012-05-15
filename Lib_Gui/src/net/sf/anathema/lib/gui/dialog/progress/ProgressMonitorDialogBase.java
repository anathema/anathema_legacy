/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.sf.anathema.lib.gui.dialog.progress;

import net.disy.commons.core.progress.IInterruptableRunnableWithProgress;
import net.disy.commons.core.progress.INonInterruptableRunnableWithProgress;
import net.disy.commons.core.progress.IRunnableContext;
import net.disy.commons.core.util.Ensure;

import java.lang.reflect.InvocationTargetException;

/**
 * A modal dialog that displays progress during a long running operation.
 *
 * @author gebhard
 */
public abstract class ProgressMonitorDialogBase
    implements
    IRunnableContext,
    IProgressMonitorStrategie {

  private boolean first = true;

  @Override
  public void run(final INonInterruptableRunnableWithProgress runnable)
      throws InvocationTargetException {
    Ensure.ensureArgumentNotNull(runnable);
    synchronized (this) {
      if (!first) {
        throw new IllegalStateException("Progress monitor dialog can only be run once."); //$NON-NLS-1$
      }
      first = false;
    }
  }

  @Override
  public void run(final IInterruptableRunnableWithProgress runnable)
      throws InterruptedException,
      InvocationTargetException {
    Ensure.ensureArgumentNotNull(runnable);
    synchronized (this) {
      if (!first) {
        throw new IllegalStateException("Progress monitor dialog can only be run once."); //$NON-NLS-1$
      }
      first = false;
    }
  }

}