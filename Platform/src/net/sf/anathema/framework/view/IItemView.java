package net.sf.anathema.framework.view;

import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.gui.swing.IDisposable;

import javax.swing.Icon;

public interface IItemView extends IView, IDisposable {

  public void setName(String newName);

  public String getName();

  public Icon getIcon();

  public void addNameChangedListener(ObjectValueListener<String> nameListener);

  public void removeNameChangedListener(ObjectValueListener<String> nameListener);
}