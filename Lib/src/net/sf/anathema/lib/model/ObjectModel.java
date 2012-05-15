package net.sf.anathema.lib.model;

public class ObjectModel<T> extends SmartChangeableModel implements IObjectModel<T> {

  private final IProperty<T> property = new DefaultProperty<T>();

  public ObjectModel() {
    this(null);
  }

  public ObjectModel(final T value) {
    property.setValue(value);
  }

  @Override
  public T getValue() {
    return getValue(property);
  }

  @Override
  public void setValue(final T value) {
    setValue(property, value);
  }

  @Override
  public String toString() {
    return "ObjectModel{" + property.getValue() + "}"; //$NON-NLS-1$ //$NON-NLS-2$
  }
}