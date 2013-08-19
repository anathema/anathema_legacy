package net.sf.anathema.fx.hero.perspective;

import javafx.scene.Node;
import net.miginfocom.layout.LC;
import net.sf.anathema.character.main.view.CharacterPane;
import net.sf.anathema.hero.display.MultipleContentView;
import net.sf.anathema.framework.view.util.OptionalView;
import net.sf.anathema.hero.advance.overview.view.OverviewDisplay;
import net.sf.anathema.lib.gui.layout.LayoutUtils;
import net.sf.anathema.platform.fx.BridgingPanel;
import net.sf.anathema.platform.fx.FxThreading;
import net.sf.anathema.platform.fx.NodeHolder;
import net.sf.anathema.swing.hero.perspective.FxOptionalOverview;
import org.tbee.javafx.scene.layout.MigPane;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;

public class TaskedCharacterPane implements CharacterPane, OverviewDisplay {

  private final MigPane paneContainer = new MigPane(new LC().wrapAfter(1));
  private final CardLayout viewStack = new CardLayout();
  private final JPanel viewPanel = new JPanel(viewStack);
  private final JPanel content = new JPanel(new BorderLayout());
  private final MigPane overviewPane = new MigPane(LayoutUtils.fillWithoutInsets().wrapAfter(1));

  public TaskedCharacterPane() {
    JComponent navigationComponent = createNavigationComponent();
    JComponent overviewComponent = createOverviewComponent();
    JComponent characterComponent = createCharacterComponent();
    content.add(navigationComponent, BorderLayout.WEST);
    content.add(characterComponent, BorderLayout.CENTER);
    content.add(overviewComponent, BorderLayout.EAST);
  }

  public OptionalView getOverview() {
    return new FxOptionalOverview(overviewPane);
  }

  @Override
  public MultipleContentView addMultipleContentView(String header) {
    return new TaskedMultipleContentView(header, paneContainer, viewStack, viewPanel);
  }

  @Override
  public void setOverviewPane(final Node node) {
    FxThreading.runOnCorrectThread(new Runnable() {
      @Override
      public void run() {
        overviewPane.getChildren().clear();
        overviewPane.add(node);
      }
    });
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
    BridgingPanel bridgingPanel = new BridgingPanel();
    bridgingPanel.init(new NodeHolder() {
      @Override
      public Node getNode() {
        return overviewPane;
      }
    });
    JComponent overviewComponent = bridgingPanel.getComponent();
    //TODO (Swing->FX) Layout properly when everything is in FX
    overviewComponent.setPreferredSize(new Dimension(200, content.getHeight()));
    return overviewComponent;
  }
}