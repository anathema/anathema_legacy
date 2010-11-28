package net.sf.anathema.lib.util;

public class Identificate implements IIdentificate {

  private final String id;

  public Identificate(String id) {
    this.id = id;
  }

  public final String getId() {
    return id;
  }

  @Override
  public String toString() {
    return id;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Identificate)) {
      return false;
    }
    Identificate other = (Identificate) obj;
    return other.id.equals(id);
  }

  @Override
  public int hashCode() {
    return getId().hashCode();
  }
}