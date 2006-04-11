package net.sf.anathema.framework.view;

import javax.swing.Icon;
import javax.swing.JComponent;

import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;

public interface IItemView {

  public JComponent getComponent();

  public void setName(String newName);

  public String getName();

  public Icon getIcon();

  public void addNameChangedListener(IObjectValueChangedListener<String> nameListener);

  public void removeNameChangedListener(IObjectValueChangedListener<String> nameListener);

  public void dispose();
}