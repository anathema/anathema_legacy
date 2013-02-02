package net.sf.anathema.framework.view.perspective;

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

  private IAnathemaModel model;
  private IResources resources;
  private ReflectionObjectFactory objectFactory;

  public PerspectivePaneFactory(IAnathemaModel model, IResources resources, ReflectionObjectFactory objectFactory) {
    this.model = model;
    this.resources = resources;
    this.objectFactory = objectFactory;
  }

  @Override
  public JComponent createContent() {
    JToolBar toolbar = new JToolBar();
    ButtonGroup buttonGroup = new ButtonGroup();
    Collection<Perspective> sortedPerspectives = objectFactory.instantiateOrdered(PerspectiveAutoCollector.class);
    final CardLayout perspectiveStack = new CardLayout();
    final JPanel cardPanel = new JPanel(perspectiveStack);
    JToggleButton buttonToSelect = null;
    for (final Perspective perspective : sortedPerspectives) {
      cardPanel.add(perspective.createContent(model, resources, objectFactory), perspective.getTitle());
      JToggleButton selectPerspectiveButton = new JToggleButton(new SmartAction(perspective.getTitle()) {
        @Override
        protected void execute(Component parentComponent) {
          perspectiveStack.show(cardPanel, perspective.getTitle());
        }
      });
      toolbar.add(selectPerspectiveButton);
      buttonGroup.add(selectPerspectiveButton);
      if (buttonToSelect == null) {
        buttonToSelect = selectPerspectiveButton;
      }
    }

    JPanel contentPanel = new JPanel(new BorderLayout());
    contentPanel.add(toolbar, BorderLayout.NORTH);
    contentPanel.add(cardPanel, BorderLayout.CENTER);

    if (buttonToSelect != null) {
      buttonToSelect.setSelected(true);
    }
    return contentPanel;
  }
}
