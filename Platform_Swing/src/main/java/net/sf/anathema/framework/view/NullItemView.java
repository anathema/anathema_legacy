package net.sf.anathema.framework.view;

import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.file.RelativePath;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class NullItemView implements SwingItemView {
  @Override
  public void setName(String newName) {
    //nothing to do
  }

  @Override
  public String getName() {
    return null;
  }

  @Override
  public RelativePath getIconPath() {
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
  public JComponent getComponent() {
    return new JPanel();
  }
}