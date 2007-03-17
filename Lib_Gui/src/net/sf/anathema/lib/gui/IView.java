package net.sf.anathema.lib.gui;

import javax.swing.JComponent;

public interface IView {

  @Deprecated //TODO change to constructor parenting
  public JComponent getComponent();
}