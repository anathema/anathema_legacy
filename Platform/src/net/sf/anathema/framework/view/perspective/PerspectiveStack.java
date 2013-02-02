package net.sf.anathema.framework.view.perspective;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.initialization.reflections.ReflectionObjectFactory;
import net.sf.anathema.lib.resources.IResources;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.CardLayout;

public class PerspectiveStack {
  private final CardLayout perspectiveStack = new CardLayout();
  private final JPanel cardPanel = new JPanel(perspectiveStack);
  private final IAnathemaModel model;
  private final IResources resources;
  private final ReflectionObjectFactory objectFactory;

  public PerspectiveStack(IAnathemaModel model, IResources resources, ReflectionObjectFactory objectFactory) {
    this.model = model;
    this.resources = resources;
    this.objectFactory = objectFactory;
  }

  public void add(Perspective perspective) {
    JComponent perspectiveContent = perspective.createContent(model, resources, objectFactory);
    cardPanel.add(perspectiveContent, perspective.getTitle());
  }

  public void show(Perspective perspective) {
    perspectiveStack.show(cardPanel, perspective.getTitle());
  }

  public JComponent getContent() {
    return cardPanel;
  }
}
