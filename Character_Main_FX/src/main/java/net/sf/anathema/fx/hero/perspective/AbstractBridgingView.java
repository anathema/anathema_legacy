package net.sf.anathema.fx.hero.perspective;

import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.platform.fx.BridgingPanel;
import net.sf.anathema.platform.fx.NodeHolder;

import javax.swing.JComponent;

public class AbstractBridgingView implements IView {

  private final BridgingPanel panel = new BridgingPanel();

  public void init(NodeHolder view, String... cssPaths) {
    panel.init(view, cssPaths);
  }

  @Override
  public JComponent getComponent() {
    return panel.getComponent();
  }
}