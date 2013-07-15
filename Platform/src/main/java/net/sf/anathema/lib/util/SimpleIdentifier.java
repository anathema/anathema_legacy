package net.sf.anathema.lib.util;

public class SimpleIdentifier implements Identifier {

  private final String id;

  public SimpleIdentifier(String id) {
    this.id = id;
  }

  @Override
  public final String getId() {
    return id;
  }

  @Override
  public String toString() {
    return id;
  }

  @Override
  public final boolean equals(Object obj) {
    if (!(obj instanceof SimpleIdentifier)) {
      return false;
    }
    SimpleIdentifier other = (SimpleIdentifier) obj;
    return other.id.equals(id);
  }

  @Override
  public final int hashCode() {
    return getId().hashCode();
  }
}