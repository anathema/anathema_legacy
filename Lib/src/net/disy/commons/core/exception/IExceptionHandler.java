/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.disy.commons.core.exception;

/**
 * Common interface for any object able to handle a {@link java.lang.Throwable} object.
 * 
 * @author gebhard
 * @published
 */
public interface IExceptionHandler {
  /**
   * Handles the given {@link Throwable} object. 
   * @param exception the {@link Throwable} object to handle.
   * @published
   */
  public void handle(Throwable exception);
}