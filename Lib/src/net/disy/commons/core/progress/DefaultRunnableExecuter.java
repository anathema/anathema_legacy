/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.disy.commons.core.progress;

public class DefaultRunnableExecuter implements IRunnableExecuter {

  private static int count = 0;
  private final String threadName;

  public DefaultRunnableExecuter(final String threadName) {
    this.threadName = threadName;
  }

  @Override
  public void execute(final Runnable runnable) {
    final String name = threadName + "-" + (++count); //$NON-NLS-1$
    final Thread thread = new Thread(new ThreadGroup(name), runnable);
    thread.setName(name);
    thread.start();
  }
}