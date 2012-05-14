package net.sf.anathema.framework.view;

import net.sf.anathema.lib.control.ObjectValueListener;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class NullItemView implements IItemView {
  @Override
  public void setName(String newName) {
    //nothing to do
  }

  @Override
  public String getName() {
    return null;
  }

  @Override
  public Icon getIcon() {
    return null;
  }

  @Override
  public void addNameChangedListener(ObjectValueListener<String> nameListener) {
    //nothing to do
  }

  @Override
  public void removeNameChangedListener(ObjectValueListener<String> nameListener) {
    //nothing to do
  }

  @Override
  public void dispose() {
    //nothing to do
  }

  @Override
  public JComponent getComponent() {
    return new JPanel();
  }
}