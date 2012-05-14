/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.disy.commons.core.progress;

/**
 * @author gebhard
 * @published
 */
public interface ICancelable {

  /** Returns whether cancelation of current operation has been requested. Long-running
   * operations should poll to see if cancelation has been requested.
   * 
   * @return <code>true</code> if cancellation has been requested, and <code>false</code>
   * otherwise
   * @published */
  public boolean isCanceled();
}