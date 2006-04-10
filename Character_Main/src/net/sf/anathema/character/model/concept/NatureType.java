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

  public String getWillpowerCondition() {
    return condition;
  }

  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof NatureType)) {
      return false;
    }
    NatureType other = (NatureType) obj;
    return super.equals(obj) && other.name.equals(name) && other.condition.equals(condition);
  }

  @Override
  public int hashCode() {
    return super.hashCode() + name.hashCode() + condition.hashCode();
  }
}