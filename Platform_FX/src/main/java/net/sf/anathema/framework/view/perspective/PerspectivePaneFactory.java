package net.sf.anathema.framework.view.perspective;

import javafx.scene.Node;
import net.miginfocom.layout.CC;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.view.ViewFactory;
import net.sf.anathema.initialization.ObjectFactory;
import net.sf.anathema.lib.gui.layout.LayoutUtils;
import net.sf.anathema.lib.resources.Resources;
import org.tbee.javafx.scene.layout.MigPane;

import java.util.Collection;

public class PerspectivePaneFactory implements ViewFactory {

  private final PerspectiveStack perspectiveStack;
  private final PerspectiveSelectionBar selectionBar;
  private final Resources resources;
  private final ObjectFactory objectFactory;

  public PerspectivePaneFactory(IApplicationModel model, Environment environment, ObjectFactory objectFactory) {
    this.resources = environment;
    this.objectFactory = objectFactory;
    this.perspectiveStack = new PerspectiveStack(model, environment);
    this.selectionBar = new PerspectiveSelectionBar(perspectiveStack);
  }

  @Override
  public Node createContent() {
    Collection<Perspective> sortedPerspectives = objectFactory.instantiateOrdered(PerspectiveAutoCollector.class);
    for (final Perspective perspective : sortedPerspectives) {
      perspectiveStack.add(perspective);
      selectionBar.addPerspective(perspective, resources);
    }
    final MigPane contentPanel = new MigPane(LayoutUtils.fillWithoutInsets());
    contentPanel.add(selectionBar.getContent(), new CC().dockNorth());
    contentPanel.add(perspectiveStack.getContent(), new CC().push().grow());
    selectionBar.selectFirstButton();
    return contentPanel;
  }
}