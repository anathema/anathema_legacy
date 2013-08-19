package net.sf.anathema.fx.hero.perspective;

import javafx.scene.Node;
import net.sf.anathema.platform.fx.NodeHolder;
import net.sf.anathema.platform.fx.ParentHolder;
import net.sf.anathema.platform.fx.Stylesheet;
import net.sf.anathema.platform.fx.ViewHolder;

public class AbstractBridgingView implements NodeHolder {

  private NodeHolder nodeHolder;

  public void init(NodeHolder view, String... cssPaths) {
    ParentHolder viewHolder = new ViewHolder(view);
    for (String cssPath : cssPaths) {
      new Stylesheet(cssPath).applyToParent(viewHolder.getParent());
    }
    this.nodeHolder = view;
  }

  @Override
  public Node getNode() {
    return nodeHolder.getNode();
  }
}