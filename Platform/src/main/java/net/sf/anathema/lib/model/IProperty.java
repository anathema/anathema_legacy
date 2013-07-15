package net.sf.anathema.lib.model;

import com.google.common.base.Supplier;

public interface IProperty<T> extends ISettable<T>, Supplier<T> {
  //nothing to do
}