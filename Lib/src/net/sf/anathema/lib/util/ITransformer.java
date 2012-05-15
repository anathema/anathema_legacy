package net.sf.anathema.lib.util;

public interface ITransformer<I, O> {
  public O transform(I input);
}