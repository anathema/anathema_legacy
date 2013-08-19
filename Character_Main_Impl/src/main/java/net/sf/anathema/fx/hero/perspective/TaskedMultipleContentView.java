package net.sf.anathema.fx.hero.perspective;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import net.miginfocom.layout.CC;
import net.sf.anathema.hero.display.ContentProperties;
import net.sf.anathema.hero.display.MultipleContentView;
import net.sf.anathema.lib.gui.layout.LayoutUtils;
import net.sf.anathema.platform.fx.BridgingPanel;
import net.sf.anathema.platform.fx.FxThreading;
import net.sf.anathema.platform.fx.NodeHolder;
import net.sf.anathema.platform.fx.StyledTitledPane;
import net.sf.anathema.swing.hero.perspective.SwitchToView;
import org.tbee.javafx.scene.layout.MigPane;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.CardLayout;

public class TaskedMultipleContentView implements MultipleContentView {
  private final MigPane contentPane = new MigPane(LayoutUtils.fillWithoutInsets().wrapAfter(1));
  private final String header;
  private final MigPane paneContainer;
  private CardLayout viewStack;
  private JPanel viewPanel;
  private boolean isEmpty = true;

  public TaskedMultipleContentView(String header, MigPane paneContainer, CardLayout viewStack, JPanel viewPanel) {
    this.header = header;
    this.paneContainer = paneContainer;
    this.viewStack = viewStack;
    this.viewPanel = viewPanel;
  }

  @Override
  public void addView(NodeHolder view, ContentProperties tabProperties) {
    final String name = tabProperties.getName();
    NodeHolder fxContainer = createContainer(view, name);
    JComponent swingComponent = connectToSwing(fxContainer);
    viewPanel.add(swingComponent, name);
    final Button trigger = new Button(name);
    trigger.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent actionEvent) {
        new SwitchToView(name, viewPanel, viewStack).execute();
      }
    });
    FxThreading.runOnCorrectThread(new Runnable() {
      @Override
      public void run() {
        contentPane.add(trigger);
      }
    });
    isEmpty = false;
  }

  @Override
  public void finishInitialization() {
    if (isEmpty) {
      return;
    }
    FxThreading.runOnCorrectThread(new Runnable() {
      @Override
      public void run() {
        Node styledPane = StyledTitledPane.Create(header, contentPane);
        paneContainer.add(styledPane, new CC().push().grow());
      }
    });
  }

  private NodeHolder createContainer(final NodeHolder content, String name) {
    final BorderPane viewComponent = new BorderPane();
    Label title = new Label(name);
    viewComponent.setTop(title);
    FxThreading.runOnCorrectThread(new Runnable() {
      @Override
      public void run() {
        viewComponent.setCenter(content.getNode());
      }
    });
    return new NodeHolder() {
      @Override
      public Node getNode() {
        return viewComponent;
      }
    };
  }

  private JComponent connectToSwing(NodeHolder content) {
    BridgingPanel bridgingPanel = new BridgingPanel();
    bridgingPanel.init(content);
    return bridgingPanel.getComponent();
  }
}
