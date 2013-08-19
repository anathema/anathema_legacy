package net.sf.anathema.swing.hero.perspective;

import javafx.scene.Node;
import net.miginfocom.layout.LC;
import net.sf.anathema.character.main.view.CharacterPane;
import net.sf.anathema.framework.presenter.view.MultipleContentView;
import net.sf.anathema.framework.view.util.OptionalView;
import net.sf.anathema.hero.advance.overview.view.OverviewDisplay;
import net.sf.anathema.platform.fx.BridgingPanel;
import net.sf.anathema.platform.fx.NodeHolder;
import org.jdesktop.swingx.JXCollapsiblePane;
import org.tbee.javafx.scene.layout.MigPane;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

public class TaskedCharacterPane implements CharacterPane, OverviewDisplay {

  private final MigPane paneContainer = new MigPane(new LC().wrapAfter(1));
  private final CardLayout viewStack = new CardLayout();
  private final JPanel viewPanel = new JPanel(viewStack);
  private final JPanel content = new JPanel(new BorderLayout());
  private final JXCollapsiblePane overviewPane = new JXCollapsiblePane(JXCollapsiblePane.Direction.RIGHT);

  public TaskedCharacterPane() {
    JComponent navigationComponent = createNavigationComponent();
    JComponent overviewComponent = createOverviewComponent();
    JComponent characterComponent = createCharacterComponent();
    content.add(navigationComponent, BorderLayout.WEST);
    content.add(characterComponent, BorderLayout.CENTER);
    content.add(overviewComponent, BorderLayout.EAST);
  }

  public OptionalView getOverview() {
    return new OverviewOptionalView(overviewPane);
  }

  @Override
  public MultipleContentView addMultipleContentView(String header) {
    return new TaskedMultipleContentView(header, paneContainer, viewStack, viewPanel);
  }

  @Override
  public void setOverviewPane(JComponent component) {
    overviewPane.removeAll();
    overviewPane.add(component);
  }

  @Override
  public JComponent getComponent() {
    return content;
  }

  private JComponent createNavigationComponent() {
    BridgingPanel bridgingPanel = new BridgingPanel();
    bridgingPanel.init(new NodeHolder() {
      @Override
      public Node getNode() {
        return paneContainer;
      }
    });
    JComponent navigationComponent = bridgingPanel.getComponent();
    //TODO (Swing->FX) Layout properly when everything is in FX
    navigationComponent.setPreferredSize(new Dimension(200, content.getHeight()));
    return navigationComponent;
  }

  private JPanel createCharacterComponent() {
    return viewPanel;
  }

  private JComponent createOverviewComponent() {
    JXCollapsiblePane overviewComponent = overviewPane;
    overviewComponent.setAnimated(false);
    overviewComponent.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
    return overviewComponent;
  }
}