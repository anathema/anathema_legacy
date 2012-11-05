package net.sf.anathema.characterengine.quality;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class QualityKey {
  public static QualityKey ForTypeAndName(String type, String name) {
    return new QualityKey(new Type(type), new Name(name));
  }

  private final Type type;
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

  public void withTypeDo(TypeClosure closure) {
    closure.execute(type);
  }

  public void withNameDo(NameClosure closure) {
    closure.execute(name);
  }

  @Override
  public String toString() {
    return "QualityKey{" +
            "type=" + type +
            ", name=" + name +
            '}';
  }
}