package net.sf.anathema.characterengine.quality;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Type {
  @SuppressWarnings("UnusedDeclaration")
  private final String type;

  public Type(String type) {
    this.type = type;
  }

  @Override
  public boolean equals(Object o) {
    return EqualsBuilder.reflectionEquals(this, o);
  }

  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this);
  }

  @Override
  public String toString() {
    return "'" + type + "'";
  }
}
