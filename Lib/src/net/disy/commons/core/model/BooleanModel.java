/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.disy.commons.core.model;

public class BooleanModel extends AbstractChangeableModel implements IModifiableBooleanModel {

  private boolean value;

  public BooleanModel() {
    this(false);
  }

  public BooleanModel(final boolean value) {
    this.value = value;
  }

  @Override
  public boolean getValue() {
    return value;
  }

  @Override
  public void setValue(final boolean selected) {
    if (this.value == selected) {
      return;
    }
    this.value = selected;
    fireChangeEvent();
  }

  @Override
  public String toString() {
    return "BooleanModel: " + value; //$NON-NLS-1$
  }

}