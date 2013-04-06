package net.sf.anathema.framework.view.item;

import com.google.common.base.Objects;
import net.sf.anathema.framework.view.IItemView;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.file.RelativePath;
import org.jmock.example.announcer.Announcer;

public abstract class AbstractItemView implements IItemView {

  private String name;
  private final Announcer<ObjectValueListener> control = Announcer.to(ObjectValueListener.class);
  private final RelativePath icon;

  protected AbstractItemView(String name, RelativePath icon) {
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
  public final RelativePath getIconPath() {
    return icon;
  }

  @Override
  public void addNameChangedListener(ObjectValueListener<String> nameListener) {
    control.addListener(nameListener);
  }

  @Override
  public void removeNameChangedListener(ObjectValueListener<String> nameListener) {
    control.removeListener(nameListener);
  }

  public void fireNameChangedEvent(String newName) {
    control.announce().valueChanged(newName);
  }
}