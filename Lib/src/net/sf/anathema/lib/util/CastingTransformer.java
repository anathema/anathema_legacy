package net.sf.anathema.lib.util;

import com.google.common.base.Function;

public final class CastingTransformer<I, O> implements Function<I, O> {
  @SuppressWarnings("unchecked")
  @Override
  public O apply(I input) {
    return (O) input;
  }
}