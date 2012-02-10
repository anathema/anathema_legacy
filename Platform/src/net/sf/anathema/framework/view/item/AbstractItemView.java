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
  
  public final void setName(String name) {
    if (Objects.equal(name, this.name)) {
      return;
    }
    this.name = name;
    fireNameChangedEvent(name);
  }

  public final String getName() {
    return name;
  }

  public final Icon getIcon() {
    return icon;
  }

  public void addNameChangedListener(IObjectValueChangedListener<String> nameListener) {
    control.addObjectValueChangeListener(nameListener);
  }

  public void removeNameChangedListener(IObjectValueChangedListener<String> nameListener) {
    control.removeObjectValueChangeListener(nameListener);
  }

  public void fireNameChangedEvent(String newName) {
    control.fireValueChangedEvent(newName);
  }

  public void dispose() {
    // Nothing to do
  }
}