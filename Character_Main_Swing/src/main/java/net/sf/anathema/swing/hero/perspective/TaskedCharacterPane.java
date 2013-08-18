package net.sf.anathema.swing.hero.perspective;

import net.sf.anathema.character.main.view.CharacterPane;
import net.sf.anathema.framework.presenter.view.MultipleContentView;
import net.sf.anathema.hero.advance.overview.view.OverviewDisplay;
import org.jdesktop.swingx.JXCollapsiblePane;
import org.jdesktop.swingx.JXTaskPane;
import org.jdesktop.swingx.JXTaskPaneContainer;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;

public class TaskedCharacterPane implements CharacterPane, OverviewDisplay {

  private final JXTaskPaneContainer paneContainer = new JXTaskPaneContainer();
  private final CardLayout viewStack = new CardLayout();
  private final JPanel viewPanel = new JPanel(viewStack);
  private final JPanel content = new JPanel(new BorderLayout());
  private final JXCollapsiblePane overview = new JXCollapsiblePane(JXCollapsiblePane.Direction.RIGHT);

  public TaskedCharacterPane() {
    content.add(paneContainer, BorderLayout.WEST);
    content.add(viewPanel, BorderLayout.CENTER);
    content.add(overview, BorderLayout.EAST);
    overview.setAnimated(false);
    overview.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
  }

  public JXCollapsiblePane getOverview() {
    return overview;
  }

  @Override
  public MultipleContentView addMultipleContentView(String header) {
    final JXTaskPane pane = new JXTaskPane();
    pane.setTitle(header);
    return new TaskedMultipleContentView(pane, viewPanel, paneContainer, viewStack);
  }

  @Override
  public void setOverview(JComponent component) {
    overview.removeAll();
    overview.add(component);
  }

  @Override
  public JComponent getComponent() {
    return content;
  }
}