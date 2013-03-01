package net.sf.anathema.framework.view.perspective;

import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.view.ViewFactory;
import net.sf.anathema.initialization.Instantiater;
import net.sf.anathema.lib.resources.IResources;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.util.Collection;

public class PerspectivePaneFactory implements ViewFactory {

  private final PerspectiveStack perspectiveStack;
  private final PerspectiveSelectionBar selectionBar;
  private final IResources resources;
  private final Instantiater instantiater;

  public PerspectivePaneFactory(IAnathemaModel model, IResources resources, Instantiater instantiater) {
    this.resources = resources;
    this.instantiater = instantiater;
    this.perspectiveStack = new PerspectiveStack(model, resources);
    this.selectionBar = new PerspectiveSelectionBar(perspectiveStack);
  }

  @Override
  public JComponent createContent() {
    Collection<Perspective> sortedPerspectives = instantiater.instantiateOrdered(PerspectiveAutoCollector.class);
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
