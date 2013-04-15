package net.sf.anathema.character.equipment.item.events;

import org.apache.commons.lang3.builder.EqualsBuilder;

public class ValueId {
  private String id;

  public ValueId(String id) {
    this.id = id;
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }

  @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
  @Override
  public boolean equals(Object obj) {
    return EqualsBuilder.reflectionEquals(this, obj);
  }
}
