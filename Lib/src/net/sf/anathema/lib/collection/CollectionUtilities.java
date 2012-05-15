/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.sf.anathema.lib.collection;

import net.sf.anathema.lib.provider.IProvider;
import net.sf.anathema.lib.provider.NullProvider;
import net.sf.anathema.lib.util.IPredicate;

public class CollectionUtilities {

  public static <T> T getFirst(final Iterable<? extends T> iterable, final IPredicate<T> predicate) {
    return getFirst(iterable, predicate, new NullProvider<T>());
  }

  public static <T> T getFirst(
      final Iterable<? extends T> iterable,
      final IPredicate<T> predicate,
      final IProvider<T> fallback) {
    if (iterable != null && predicate != null) {
      for (final T item : iterable) {
        if (predicate.evaluate(item)) {
          return item;
        }
      }
    }
    return fallback.getObject();
  }
}