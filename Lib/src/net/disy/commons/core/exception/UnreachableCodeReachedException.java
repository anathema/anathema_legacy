/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.disy.commons.core.exception;

/** @published */
//Schmieder, FhG / LfU 8.10.2005
public class UnreachableCodeReachedException extends RuntimeException {

  public UnreachableCodeReachedException() {
    this(null, null);
  }

  public UnreachableCodeReachedException(final String s) {
    this(s, null);
  }

  /** @published */
  //Schmieder, FhG / LfU 8.10.2005
  public UnreachableCodeReachedException(final Throwable nestedException) {
    this(null, nestedException);
  }

  public UnreachableCodeReachedException(final String message, final Throwable nestedException) {
    super(message, nestedException);
  }

}