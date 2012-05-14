/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.disy.commons.swing.dialog.progress;

import net.disy.commons.core.progress.IInterruptableRunnableWithProgress;
import net.disy.commons.core.progress.INonInterruptableRunnableWithProgress;

import java.lang.reflect.InvocationTargetException;

public interface IProgressMonitorStrategie {

  public abstract void run(final INonInterruptableRunnableWithProgress runnable)
      throws InvocationTargetException;

  public abstract void run(final IInterruptableRunnableWithProgress runnable)
      throws InterruptedException,
      InvocationTargetException;

}