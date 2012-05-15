/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.sf.anathema.lib.model;

public class ObjectModel<T> extends SmartChangeableModel implements IObjectModel<T> {

  private final IProperty<T> property = new DefaultProperty<T>();

  public ObjectModel() {
    this(null);
  }

  public ObjectModel(final T value) {
    property.setValue(value);
  }

  @Override
  public T getValue() {
    return getValue(property);
  }

  @Override
  public void setValue(final T value) {
    setValue(property, value);
  }

  @Override
  public String toString() {
    return "ObjectModel{" + property.getValue() + "}"; //$NON-NLS-1$ //$NON-NLS-2$
  }
}