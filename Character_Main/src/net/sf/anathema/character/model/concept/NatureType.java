package net.sf.anathema.character.model.concept;

import net.sf.anathema.lib.util.Identificate;

public class NatureType extends Identificate implements INatureType {

  private final String name;
  private final String condition;

  public NatureType(String id, String name, String condition) {
    super(id);
    this.name = name;
    this.condition = condition;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof NatureType)) {
      return false;
    }
    return super.equals(obj);
  }

  @Override
  public int hashCode() {
    return super.hashCode() + name.hashCode() + condition.hashCode();
  }
}