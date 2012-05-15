package net.sf.anathema.lib.util;

public final class CastingTransformer<I, O> implements ITransformer<I, O> {
  @SuppressWarnings("unchecked")
  @Override
  public O transform(final I input) {
    return (O) input;
  }
}