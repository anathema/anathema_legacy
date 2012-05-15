/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.sf.anathema.lib.number;

public class MaxIntegerValueBuilder {

  private int maxValue;

  public MaxIntegerValueBuilder(final int startValue) {
    this.maxValue = startValue;
  }

  public int getMaximum() {
    return maxValue;
  }

  public void add(final int value) {
    maxValue = value > maxValue ? value : maxValue;
  }
}