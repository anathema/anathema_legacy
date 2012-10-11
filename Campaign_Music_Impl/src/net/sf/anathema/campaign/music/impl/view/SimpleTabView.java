package net.sf.anathema.campaign.music.impl.view;

import net.sf.anathema.framework.presenter.view.IInitializableContentView;

import javax.swing.JComponent;

public class SimpleTabView implements IInitializableContentView<Object> {
  private final JComponent component;

  public SimpleTabView(JComponent component) {
    this.component = component;
  }

  @Override
  public JComponent getComponent() {
    return component;
  }

  @Override
  public void initGui(Object properties) {
    // Nothing to do
  }
}