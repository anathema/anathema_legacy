package net.sf.anathema.framework.view;

import javax.swing.Icon;

import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.gui.IDisposable;
import net.sf.anathema.lib.gui.IView;

public interface IItemView extends IView, IDisposable {

  public void setName(String newName);

  public String getName();

  public Icon getIcon();

  public void addNameChangedListener(IObjectValueChangedListener<String> nameListener);

  public void removeNameChangedListener(IObjectValueChangedListener<String> nameListener);
}