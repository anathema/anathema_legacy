package net.sf.anathema.character.impl.view;

import net.sf.anathema.framework.presenter.view.MultipleContentView;
import net.sf.anathema.framework.view.util.ContentProperties;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.gui.action.SmartAction;
import org.jdesktop.swingx.JXCollapsiblePane;
import org.jdesktop.swingx.JXTaskPane;
import org.jdesktop.swingx.JXTaskPaneContainer;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.FlowLayout;

public class TaskedCharacterPane implements CharacterPane {

  private final JXTaskPaneContainer paneContainer = new JXTaskPaneContainer();
  private final CardLayout viewStack = new CardLayout();
  private final JPanel viewPanel = new JPanel(viewStack);
  private final JPanel content = new JPanel(new BorderLayout());
  private final JXCollapsiblePane overview = new JXCollapsiblePane(JXCollapsiblePane.Direction.RIGHT,
          new FlowLayout(FlowLayout.CENTER, 0, 0));

  public TaskedCharacterPane() {
    content.add(paneContainer, BorderLayout.WEST);
    content.add(viewPanel, BorderLayout.CENTER);
    content.add(overview, BorderLayout.EAST);
    overview.setAnimated(false);
  }

  public JXCollapsiblePane getOverview() {
    return overview;
  }

  @Override
  public MultipleContentView addMultipleContentView(String header) {
    final JXTaskPane pane = new JXTaskPane();
    pane.setTitle(header);
    paneContainer.add(pane);
    return new MultipleContentView() {
      @Override
      public void addView(IView view, final ContentProperties tabProperties) {
        viewPanel.add(createContainer(view), tabProperties.getName());
        pane.add(new SmartAction() {
          {
            setNameWithoutMnemonic(tabProperties.getName());
          }

          @Override
          public void execute(Component parent) {
            viewStack.show(viewPanel, tabProperties.getName());
          }
        });
      }

      @Override
      public JComponent getComponent() {
        return null;
      }
    };
  }

  @Override
  public void setOverview(JComponent component) {
    overview.removeAll();
    overview.add(component);
  }

  private JComponent createContainer(IView content) {
    JPanel viewComponent = new JPanel(new BorderLayout());
    viewComponent.setBorder(new EmptyBorder(10, 10, 10, 10));
    viewComponent.add(content.getComponent());
    return viewComponent;
  }

  @Override
  public JComponent getComponent() {
    return content;
  }
}
