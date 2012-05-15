package net.sf.anathema.lib.util;

public interface IPredicate<T> {

  boolean evaluate(T value);
}