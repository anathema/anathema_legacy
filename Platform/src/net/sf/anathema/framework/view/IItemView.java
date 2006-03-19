package net.sf.anathema.framework.view;

import javax.swing.Icon;
import javax.swing.JComponent;

import net.sf.anathema.lib.control.stringvalue.IStringValueChangedListener;

public interface IItemView {

  public JComponent getComponent();

  public void setName(String newName);

  public String getName();
  
  public Icon getIcon();

  public void addNameChangedListener(IStringValueChangedListener nameListener);

  public void removeNameChangedListener(IStringValueChangedListener nameListener);

  public void dispose();
}