package net.sf.anathema.fx.hero.perspective;

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import net.miginfocom.layout.LC;
import net.sf.anathema.character.main.view.CharacterPane;
import net.sf.anathema.framework.view.util.OptionalView;
import net.sf.anathema.hero.advance.overview.view.OverviewDisplay;
import net.sf.anathema.hero.display.MultipleContentView;
import net.sf.anathema.lib.gui.layout.LayoutUtils;
import net.sf.anathema.platform.fx.BridgingPanel;
import net.sf.anathema.platform.fx.FxThreading;
import net.sf.anathema.platform.fx.NodeHolder;
import net.sf.anathema.swing.hero.perspective.FxOptionalOverview;
import org.tbee.javafx.scene.layout.MigPane;

import javax.swing.JComponent;

public class TaskedCharacterPane implements CharacterPane, OverviewDisplay {

  private final MigPane paneContainer = new MigPane(new LC().wrapAfter(1));
  private final MigPane viewPanel = new MigPane();
  private final BorderPane content = new BorderPane();
  private final MigPane overviewPane = new MigPane(LayoutUtils.fillWithoutInsets().wrapAfter(1));
  private final BridgingPanel panel = new BridgingPanel();

  public TaskedCharacterPane() {
    content.setLeft(paneContainer);
    content.setCenter(viewPanel);
    content.setRight(overviewPane);
    panel.init(new NodeHolder() {
      @Override
      public Node getNode() {
        return content;
      }
    });
  }

  public OptionalView getOverview() {
    return new FxOptionalOverview(overviewPane);
  }

  @Override
  public MultipleContentView addMultipleContentView(String header) {
    return new TaskedMultipleContentView(header, paneContainer, viewPanel);
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

  public JComponent getComponent() {
    return panel.getComponent();
  }
}