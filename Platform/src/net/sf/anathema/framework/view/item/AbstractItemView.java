package net.sf.anathema.framework.view.item;

import javax.swing.Icon;

import net.sf.anathema.framework.view.IItemView;
import net.sf.anathema.lib.control.stringvalue.IStringValueChangedListener;
import net.sf.anathema.lib.control.stringvalue.StringValueControl;

public abstract class AbstractItemView implements IItemView {

  private String name;
  private final StringValueControl control = new StringValueControl();
  private final Icon icon;

  protected AbstractItemView(String name, Icon icon) {
    this.name = name;
    this.icon = icon;
  }

  public final void setName(String name) {
    if (this.name == name) {
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

  public void addNameChangedListener(IStringValueChangedListener nameListener) {
    control.addStringValueChangeListener(nameListener);
  }

  public void removeNameChangedListener(IStringValueChangedListener nameListener) {
    control.removeStringValueChangeListener(nameListener);
  }

  public void fireNameChangedEvent(String newName) {
    control.fireValueChangedEvent(newName);
  }

  public void dispose() {
    // Nothing to do
  }
}