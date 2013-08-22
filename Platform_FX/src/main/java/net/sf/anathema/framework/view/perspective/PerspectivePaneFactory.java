package net.sf.anathema.framework.view.perspective;

import javafx.scene.Node;
import net.miginfocom.layout.CC;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.view.ViewFactory;
import net.sf.anathema.initialization.ObjectFactory;
import net.sf.anathema.lib.gui.layout.LayoutUtils;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.platform.fx.BridgingPanel;
import net.sf.anathema.platform.fx.NodeHolder;
import org.tbee.javafx.scene.layout.MigPane;

import javax.swing.JComponent;
import java.util.Collection;

public class PerspectivePaneFactory implements ViewFactory {

  private final PerspectiveStack perspectiveStack;
  private final PerspectiveSelectionBar selectionBar;
  private final Resources resources;
  private final ObjectFactory objectFactory;

  public PerspectivePaneFactory(IApplicationModel model, Resources resources, ObjectFactory objectFactory) {
    this.resources = resources;
    this.objectFactory = objectFactory;
    this.perspectiveStack = new PerspectiveStack(model, resources);
    this.selectionBar = new PerspectiveSelectionBar(perspectiveStack);
  }

  @Override
  public JComponent createContent() {
    BridgingPanel bridgingPanel = new BridgingPanel();
    Collection<Perspective> sortedPerspectives = objectFactory.instantiateOrdered(PerspectiveAutoCollector.class);
    for (final Perspective perspective : sortedPerspectives) {
      perspectiveStack.add(perspective);
      selectionBar.addPerspective(perspective, resources);
    }
    final MigPane contentPanel = new MigPane(LayoutUtils.fillWithoutInsets());
    contentPanel.add(selectionBar.getContent(), new CC().alignX("50%").wrap());
    contentPanel.add(perspectiveStack.getContent(), new CC().push().grow());
    selectionBar.selectFirstButton();
    bridgingPanel.init(new NodeHolder() {
      @Override
      public Node getNode() {
        return contentPanel;
      }
    });
    return bridgingPanel.getComponent();
  }
}