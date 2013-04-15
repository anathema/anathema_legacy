package net.sf.anathema.character.equipment.item.events;

public class UserInput {
  private final ValueId id;
  private final Object value;

  public UserInput(ValueId id, Object value) {
    this.id = id;
    this.value = value;
  }

  public boolean is(ValueId query) {
    return id.equals(query);
  }

  @SuppressWarnings("unchecked")
  public <T> T as(Class<T> aClass) {
    return (T) value;
  }
}