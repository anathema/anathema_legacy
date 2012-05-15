/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.disy.commons.core.exception;

import java.lang.Thread.UncaughtExceptionHandler;

/**
 * Catches all {@link java.lang.Throwable} objects invoked in AWT event dispatch threads or in
 * Threads. The {@link java.lang.Throwable} objects will be delegated to an attached {@link
 * net.disy.commons.core.exception.IExceptionHandler} object, see {@link
 * #setHandler(IExceptionHandler)}.
 * @published
 */
public class CentralExceptionHandling {

  private static final CentralExceptionHandling instance = new CentralExceptionHandling();

  private IExceptionHandler handler;

  private CentralExceptionHandling() {
    attachForEventDispatchExceptionHandling();
    attachForThreadUncaughtExceptionHandling();
  }

  private void attachForThreadUncaughtExceptionHandling() {
    Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {
      @Override
      public void uncaughtException(final Thread t, final Throwable e) {
        handle(e);
      }
    });
  }

  public static void setHandler(final IExceptionHandler handler) {
    getInstance().handler = handler;
  }

  public static CentralExceptionHandling getInstance() {
    return instance;
  }

  public void handle(final Throwable exception) {
    if (handler != null) {
      handler.handle(exception);
    }
    else {
      System.err.println("Exception occurred during event dispatching:"); //$NON-NLS-1$
      exception.printStackTrace();
    }
  }

  private void attachForEventDispatchExceptionHandling() {
    System.setProperty("sun.awt.exception.handler", InternalAwtExceptionHandler.class.getName()); //$NON-NLS-1$
  }
}