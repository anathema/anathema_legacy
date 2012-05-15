/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.sf.anathema.lib.util;

public class Range {

  private final int lowerBound;
  private final int upperBound;

  public Range(final int lowerBound, final int upperBound) {
    if (lowerBound > upperBound) {
      throw new IllegalArgumentException("LowerBound may not be less than upperBound: " //$NON-NLS-1$
          + lowerBound
          + " " //$NON-NLS-1$
          + upperBound);
    }
    this.lowerBound = lowerBound;
    this.upperBound = upperBound;
  }

  public int getLowerBound() {
    return lowerBound;
  }

  public int getUpperBound() {
    return upperBound;
  }

  @Override
  public String toString() {
    return "Range[" + getLowerBound() + "," + getUpperBound() + "]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
  }
}