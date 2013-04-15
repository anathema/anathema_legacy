package net.sf.anathema.character.equipment.item.events;

public class ViewShouldShow {
  public final ValueId id;
  public final Object value;

  public ViewShouldShow(ValueId id, Object value) {
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