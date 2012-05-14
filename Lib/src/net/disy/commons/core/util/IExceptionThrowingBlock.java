/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.disy.commons.core.util;

/**
 * A block of code without parameters to be executed.
 * 
 * @see SimpleBlock
 */
public interface IExceptionThrowingBlock<T extends Throwable> {

  public void execute() throws T;
}