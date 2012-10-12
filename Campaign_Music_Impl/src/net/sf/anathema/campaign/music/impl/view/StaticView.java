package net.sf.anathema.campaign.music.impl.view;

import net.sf.anathema.lib.gui.IView;

import javax.swing.JComponent;

public class StaticView implements IView {
  private final JComponent component;

  public StaticView(JComponent component) {
    this.component = component;
  }

  @Override
  public JComponent getComponent() {
    return component;
  }
}