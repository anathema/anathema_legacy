package net.sf.anathema.fx.hero.perspective;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import net.miginfocom.layout.CC;
import net.sf.anathema.hero.display.ContentProperties;
import net.sf.anathema.hero.display.MultipleContentView;
import net.sf.anathema.lib.gui.layout.LayoutUtils;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;
import net.sf.anathema.platform.fx.FxThreading;
import net.sf.anathema.platform.fx.NodeHolder;
import net.sf.anathema.platform.fx.StyledTitledPane;
import org.tbee.javafx.scene.layout.MigPane;

public class TaskedMultipleContentView implements MultipleContentView {
  private final MigPane contentPane = new MigPane(LayoutUtils.fillWithoutInsets().wrapAfter(1));
  private final String header;
  private final MigPane paneContainer;
  private final FxStack stack;
  private boolean isEmpty = true;

  public TaskedMultipleContentView(String header, MigPane paneContainer, MigPane viewPanel) {
    this.header = header;
    this.paneContainer = paneContainer;
    this.stack = new FxStack(viewPanel);
  }

  @Override
  public void addView(NodeHolder view, ContentProperties tabProperties) {
    String name = tabProperties.getName();
    Node fxContainer = createContainer(view, name);
    final Identifier containerId = new SimpleIdentifier(name);
    stack.add(containerId, fxContainer);
    final Button trigger = new Button(name);
    trigger.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent actionEvent) {
        new SwitchToView(containerId, stack).execute();
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

  private Node createContainer(final NodeHolder content, String name) {
    final MigPane viewComponent = new MigPane(LayoutUtils.fillWithoutInsets().wrapAfter(1));
    MigPane titlePane = new MigPane(LayoutUtils.fillWithoutInsets());
    Label title = new Label(name);
    title.setStyle("-fx-font-weight: bold");
    titlePane.add(title);
    titlePane.add(new Separator(), new CC().pushX().growX());
    viewComponent.add(titlePane, new CC().pushX().growX());
    FxThreading.runOnCorrectThread(new Runnable() {
      @Override
      public void run() {
        viewComponent.add(content.getNode(), new CC().push().grow());
      }
    });
    return viewComponent;
  }
}