package net.sf.anathema.framework.view.perspective;

import javafx.scene.Node;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.view.util.FxStack;
import net.sf.anathema.lib.gui.layout.LayoutUtils;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;
import org.tbee.javafx.scene.layout.MigPane;

public class PerspectiveStack {
  private final MigPane cardPanel = new MigPane(LayoutUtils.fillWithoutInsets());
  private final FxStack perspectiveStack = new FxStack(cardPanel);
  private final IApplicationModel model;
  private final Resources resources;

  public PerspectiveStack(IApplicationModel model, Resources resources) {
    this.model = model;
    this.resources = resources;
  }

  public void add(Perspective perspective) {
    Container container = new CardContainer(getIdFor(perspective), perspectiveStack);
    perspective.initContent(container, model, resources);
  }

  public void show(Perspective perspective) {
    perspectiveStack.show(getIdFor(perspective));
  }

  private Identifier getIdFor(Perspective perspective) {
    return new SimpleIdentifier(perspective.getClass().getCanonicalName());
  }

  public Node getContent() {
    return cardPanel;
  }

  public void showFirst() {
    perspectiveStack.showFirst();
  }
}
