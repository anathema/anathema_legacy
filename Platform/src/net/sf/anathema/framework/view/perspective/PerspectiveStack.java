package net.sf.anathema.framework.view.perspective;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.lib.resources.IResources;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.CardLayout;

public class PerspectiveStack {
  private final CardLayout perspectiveStack = new CardLayout();
  private final JPanel cardPanel = new JPanel(perspectiveStack);
  private final IAnathemaModel model;
  private final IResources resources;

  public PerspectiveStack(IAnathemaModel model, IResources resources) {
    this.model = model;
    this.resources = resources;
  }

  public void add(Perspective perspective) {
    Container container = new CardContainer(getIdFor(perspective), cardPanel);
    perspective.initContent(container, model, resources);
  }

  public void show(Perspective perspective) {
    perspectiveStack.show(cardPanel, getIdFor(perspective));
  }

  private String getIdFor(Perspective perspective) {
    return perspective.getClass().getCanonicalName();
  }

  public JComponent getContent() {
    return cardPanel;
  }
}
