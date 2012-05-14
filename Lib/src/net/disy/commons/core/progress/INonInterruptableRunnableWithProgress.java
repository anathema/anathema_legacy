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
 * The INonInterruptableRunnableWithProgress interface should be implemented by any class whose instances are
 * intended to be executed as a long-running operation. Long-running operations are typically
 * presented at the UI via a modal dialog showing a progress indicator.
 * The class must define a run method that takes a progress monitor.
 * 
 *<p>The run method is usually not invoked directly, but rather by passing the
 * <code>INonInterruptableRunnableWithProgress</code> to the run method of an {@link IRunnableContext}, which
 * provides the UI for the progress monitor.
 * 
 * @author gebhard */
public interface INonInterruptableRunnableWithProgress {

  /** Runs this operation. Progress should be reported to the given progress monitor. This
   * method is usually invoked by an IRunnableContext's run method, which supplies the progress
   * monitor. 
   * 
   * @param monitor the progress monitor to use to display progress
   */
  public void run(IProgressMonitor monitor) throws InvocationTargetException;

}