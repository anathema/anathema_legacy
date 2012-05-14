/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.disy.commons.core.model;

import net.disy.commons.core.util.ObjectUtilities;

public class DefaultProperty<T> implements IProperty<T> {

  private T value;

  @Override
  public T getValue() {
    return value;
  }

  @Override
  public void setValue(final T value) {
    this.value = value;
  }

  @Override
  public boolean equals(final Object object) {
    if (!(object instanceof DefaultProperty)) {
      return false;
    }
    final DefaultProperty<?> other = (DefaultProperty<?>) object;
    return ObjectUtilities.equals(value, other.value);
  }

  @Override
  public int hashCode() {
    return ObjectUtilities.getHashCode(value);
  }
}