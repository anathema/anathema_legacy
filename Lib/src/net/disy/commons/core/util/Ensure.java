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
 * Provides convenient methods for checking contract parameters.
 */
public class Ensure {

  public static void ensureNotNull(final String message, final Object object) {
    ensureTrue(message, object != null);
  }

  public static void ensureArgumentNotNull(final String message, final Object object)
      throws IllegalArgumentException {
    ensureArgumentTrue(message, object != null);
  }

  public static void ensureNotNull(final Object object) {
    ensureNotNull("Object must not be null", object); //$NON-NLS-1$
  }

  public static void ensureArgumentNotNull(final Object object) throws IllegalArgumentException {
    ensureArgumentNotNull("Object must not be null", object); //$NON-NLS-1$
  }

  public static void ensureTrue(final String message, final boolean state) {
    if (!state) {
      throw new ContractFailedException(message);
    }
  }

  public static void ensureArgumentTrue(final String message, final boolean state)
      throws IllegalArgumentException {
    if (!state) {
      throw new IllegalArgumentException(message);
    }
  }

  public static void ensureArgumentArrayContentsNotNull(final Object[] arguments) {
    if (arguments == null) {
      return;
    }
    final String message = "Array contents must not be null"; //$NON-NLS-1$
    for (int i = 0; i < arguments.length; i++) {
      ensureArgumentNotNull(message + " at index " + i + " (0.." + (arguments.length - 1) + ')', //$NON-NLS-1$ //$NON-NLS-2$
          arguments[i]);
    }
  }

}