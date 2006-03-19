package net.sf.anathema.lib.lang.clone;

import net.disy.commons.core.exception.UnreachableCodeReachedException;
import net.sf.anathema.lib.lang.ReflectionEqualsObject;

public class ReflectionCloneableObject extends ReflectionEqualsObject implements ICloneable {

  @Override
  public ReflectionCloneableObject clone() {
    try {
      return (ReflectionCloneableObject) super.clone();
    }
    catch (CloneNotSupportedException e) {
      throw new UnreachableCodeReachedException();
    }
  }
}