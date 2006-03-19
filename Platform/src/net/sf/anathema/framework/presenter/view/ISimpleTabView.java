package net.sf.anathema.framework.presenter.view;

import javax.swing.JComponent;

public interface ISimpleTabView {

  public JComponent getComponent();
  
  public boolean needsScrollbar();
}