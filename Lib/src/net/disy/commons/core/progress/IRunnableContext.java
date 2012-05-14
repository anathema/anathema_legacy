/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.disy.commons.core.progress;

import java.lang.reflect.InvocationTargetException;

/**
 * Interface for UI components which can execute a long-running operation. The context is
 * responsible for displaying a progress indicator and optional Cancel button to the end user while
 * the operation is in progress; the context supplies a progress monitor to be used from code
 * running inside the operation. Note that an {@link IRunnableContext} is not a runnable itself.
 * 
 * @author gebhard
 */
public interface IRunnableContext {

  /**
   * Runs the given {@link IInterruptableRunnableWithProgress} in this context. For example, if this
   * is a ProgressMonitorDialog then the runnable is run using this dialog's progress monitor.
   * 
   * @param runnable the runnable to run
   * @throws InvocationTargetException wraps any exception or error which occurs while running the
   *           runnable
   * @throws InterruptedException propagated by the context if the runnable acknowledges cancelation
   *           by throwing this exception.
   * @see #run(INonInterruptableRunnableWithProgress)
   */
  public void run(IInterruptableRunnableWithProgress runnable)
      throws InterruptedException,
      InvocationTargetException;

  /**
   * Runs the given {@link INonInterruptableRunnableWithProgress} in this context. For example, if
   * this is a ProgressMonitorDialog then the runnable is run using this dialog's progress monitor.
   * 
   * @param runnable the runnable to run
   * @throws InvocationTargetException wraps any exception or error which occurs while running the
   *           runnable
   * @see #run(IInterruptableRunnableWithProgress)
   */
  public void run(INonInterruptableRunnableWithProgress runnable) throws InvocationTargetException;
}