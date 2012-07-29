package net.sf.anathema.characterengine;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class QualityKey {
  @SuppressWarnings("UnusedDeclaration")
  private final Type type;
  @SuppressWarnings("UnusedDeclaration")
  private final Name name;

  public QualityKey(Type type, Name name) {
    this.type = type;
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    return EqualsBuilder.reflectionEquals(this, o);
  }

  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this);
  }
}
