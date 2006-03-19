package net.sf.anathema.framework.view.item;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;

import net.sf.anathema.framework.view.IItemView;
import net.sf.anathema.lib.control.stringvalue.IStringValueChangedListener;

public abstract class AbstractItemView implements IItemView {

  private String name;
  private List<IStringValueChangedListener> nameListeners = new ArrayList<IStringValueChangedListener>();
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

  public synchronized final void addNameChangedListener(IStringValueChangedListener nameListener) {
    nameListeners.add(nameListener);
  }

  public synchronized final void removeNameChangedListener(IStringValueChangedListener nameListener) {
    nameListeners.remove(nameListener);
  }

  public synchronized final void fireNameChangedEvent(String newName) {
    List<IStringValueChangedListener> clonedListeners = new ArrayList<IStringValueChangedListener>(nameListeners);
    for (IStringValueChangedListener listener : clonedListeners) {
      listener.valueChanged(newName);
    }
  }

  public void dispose() {
    // Nothing to do
  }
}