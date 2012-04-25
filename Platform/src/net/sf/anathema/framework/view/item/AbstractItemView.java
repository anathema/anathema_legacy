package net.sf.anathema.framework.view.item;

import com.google.common.base.Objects;
import net.sf.anathema.framework.view.IItemView;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.control.objectvalue.ObjectValueControl;

import javax.swing.*;

public abstract class AbstractItemView implements IItemView {

  private String name;
  private final ObjectValueControl<String> control = new ObjectValueControl<String>();
  private final Icon icon;

  protected AbstractItemView(String name, Icon icon) {
    this.name = name;
    this.icon = icon;
  }
  
  @Override
  public final void setName(String name) {
    if (Objects.equal(name, this.name)) {
      return;
    }
    this.name = name;
    fireNameChangedEvent(name);
  }

  @Override
  public final String getName() {
    return name;
  }

  @Override
  public final Icon getIcon() {
    return icon;
  }

  @Override
  public void addNameChangedListener(IObjectValueChangedListener<String> nameListener) {
    control.addObjectValueChangeListener(nameListener);
  }

  @Override
  public void removeNameChangedListener(IObjectValueChangedListener<String> nameListener) {
    control.removeObjectValueChangeListener(nameListener);
  }

  public void fireNameChangedEvent(String newName) {
    control.fireValueChangedEvent(newName);
  }

  @Override
  public void dispose() {
    // Nothing to do
  }
}