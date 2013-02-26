package net.sf.anathema.framework.view.perspective;

import net.miginfocom.layout.CC;
import net.miginfocom.layout.DimConstraint;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.view.ViewFactory;
import net.sf.anathema.initialization.reflections.ReflectionObjectFactory;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.resources.IResources;

import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.util.Collection;

public class PerspectivePaneFactory implements ViewFactory {

  private final PerspectiveStack perspectiveStack;
  private final PerspectiveSelectionBar selectionBar;
  private IResources resources;
  private final ReflectionObjectFactory objectFactory;

  public PerspectivePaneFactory(IAnathemaModel model, IResources resources, ReflectionObjectFactory objectFactory) {
    this.resources = resources;
    this.objectFactory = objectFactory;
    this.perspectiveStack = new PerspectiveStack(model, resources, objectFactory);
    this.selectionBar = new PerspectiveSelectionBar(perspectiveStack);
  }

  @Override
  public JComponent createContent() {
    Collection<Perspective> sortedPerspectives = objectFactory.instantiateOrdered(PerspectiveAutoCollector.class);
    for (final Perspective perspective : sortedPerspectives) {
      perspectiveStack.add(perspective);
      selectionBar.addPerspective(perspective, resources);
    }
    JPanel contentPanel = new JPanel(new MigLayout());
    contentPanel.add(selectionBar.getContent(), new CC().alignX("50%").wrap());
    contentPanel.add(perspectiveStack.getContent(), new CC().push().grow());
    selectionBar.selectFirstButton();
    return contentPanel;
  }
}
