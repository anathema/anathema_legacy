package net.sf.anathema.fx.hero.perspective;

import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.platform.fx.BridgingPanel;
import net.sf.anathema.platform.fx.NodeHolder;
import net.sf.anathema.platform.fx.ParentHolder;
import net.sf.anathema.platform.fx.Stylesheet;
import net.sf.anathema.platform.fx.ViewHolder;

import javax.swing.JComponent;

public class AbstractBridgingView implements IView {

  private final BridgingPanel panel = new BridgingPanel();

  public void init(NodeHolder view, String... cssPaths) {
    ParentHolder viewHolder = new ViewHolder(view);
    for (String cssPath : cssPaths) {
      new Stylesheet(cssPath).applyToParent(viewHolder.getParent());
    }
    panel.init(viewHolder);
  }

  @Override
  public JComponent getComponent() {
    return panel.getComponent();
  }
}