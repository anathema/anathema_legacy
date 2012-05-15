package net.sf.anathema.lib.util;

public interface ITransformer<I, O> {
  O transform(I input);
}