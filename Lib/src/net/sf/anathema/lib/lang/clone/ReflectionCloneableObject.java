package net.sf.anathema.lib.lang.clone;

import net.disy.commons.core.exception.UnreachableCodeReachedException;
import net.sf.anathema.lib.lang.ReflectionEqualsObject;

public class ReflectionCloneableObject<V extends Cloneable> extends ReflectionEqualsObject implements ICloneable<V> {

  @SuppressWarnings("unchecked")
  @Override
  public V clone() {
    try {
      return (V) super.clone();
    }
    catch (CloneNotSupportedException e) {
      throw new UnreachableCodeReachedException();
    }
  }
}