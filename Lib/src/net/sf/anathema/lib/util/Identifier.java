package net.sf.anathema.lib.util;

public class Identifier implements Identified {

  private final String id;

  public Identifier(String id) {
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
    if (!(obj instanceof Identifier)) {
      return false;
    }
    Identifier other = (Identifier) obj;
    return other.id.equals(id);
  }

  @Override
  public final int hashCode() {
    return getId().hashCode();
  }
}