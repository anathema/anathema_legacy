package net.sf.anathema.lib.collection;

import net.sf.anathema.lib.provider.NullProvider;
import net.sf.anathema.lib.provider.Provider;
import net.sf.anathema.lib.util.IPredicate;

public class CollectionUtilities {

  public static <T> T getFirst(final Iterable<? extends T> iterable, final IPredicate<T> predicate) {
    return getFirst(iterable, predicate, new NullProvider<T>());
  }

  public static <T> T getFirst(
      final Iterable<? extends T> iterable,
      final IPredicate<T> predicate,
      final Provider<T> fallback) {
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