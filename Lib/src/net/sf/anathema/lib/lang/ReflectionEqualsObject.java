package net.sf.anathema.lib.lang;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class ReflectionEqualsObject {

  @Override
  public final boolean equals(Object obj) {
    return EqualsBuilder.reflectionEquals(this, obj);
  }

  @Override
  public final int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this);
  }
}