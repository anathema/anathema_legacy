package net.sf.anathema.lib.lang;

public interface ITransformer<I, R> {

  public R transform(I input);
}