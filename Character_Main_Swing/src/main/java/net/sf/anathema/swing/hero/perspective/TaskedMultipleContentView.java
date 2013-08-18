package net.sf.anathema.swing.hero.perspective;

import net.sf.anathema.framework.presenter.view.MultipleContentView;
import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.framework.view.util.ContentProperties;
import org.jdesktop.swingx.JXTaskPane;
import org.jdesktop.swingx.JXTaskPaneContainer;
import org.jdesktop.swingx.JXTitledSeparator;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;

class TaskedMultipleContentView implements MultipleContentView {
  private final JXTaskPane pane;
  private final JPanel viewPanel;
  private final JXTaskPaneContainer paneContainer;
  private CardLayout viewStack;
  private boolean isEmpty = true;

  public TaskedMultipleContentView(JXTaskPane pane, JPanel viewPanel, JXTaskPaneContainer paneContainer, CardLayout viewStack) {
    this.pane = pane;
    this.viewPanel = viewPanel;
    this.paneContainer = paneContainer;
    this.viewStack = viewStack;
  }

  @Override
  public void addView(IView view, final ContentProperties tabProperties) {
    String name = tabProperties.getName();
    viewPanel.add(createContainer(view, name), name);
    pane.add(new SwitchToView(name, viewPanel, viewStack));
    isEmpty = false;
  }

  @Override
  public void finishInitialization() {
    if (isEmpty) {
      return;
    }
    paneContainer.add(pane);
  }

  private JComponent createContainer(IView content, String name) {
    JPanel viewComponent = new JPanel(new BorderLayout());
    JXTitledSeparator title = new JXTitledSeparator(name);
    title.setBorder(new EmptyBorder(0, 0, 5, 0));
    title.setFont(title.getFont().deriveFont(Font.BOLD));
    viewComponent.add(title, BorderLayout.NORTH);
    viewComponent.setBorder(new EmptyBorder(10, 10, 10, 10));
    viewComponent.add(content.getComponent(), BorderLayout.CENTER);
    return viewComponent;
  }
}
