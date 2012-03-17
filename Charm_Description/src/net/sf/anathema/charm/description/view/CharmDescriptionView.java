package net.sf.anathema.charm.description.view;

import net.sf.anathema.framework.view.IItemView;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class CharmDescriptionView implements IItemView {
  @Override
  public void setName(String newName) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public String getName() {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public Icon getIcon() {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void addNameChangedListener(IObjectValueChangedListener<String> nameListener) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void removeNameChangedListener(IObjectValueChangedListener<String> nameListener) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void dispose() {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public JComponent getComponent() {
    return new JPanel();
  }
}
