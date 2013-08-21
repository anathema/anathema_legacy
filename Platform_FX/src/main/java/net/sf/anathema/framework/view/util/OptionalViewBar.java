package net.sf.anathema.framework.view.util;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import net.miginfocom.layout.CC;
import net.sf.anathema.lib.gui.layout.LayoutUtils;
import org.tbee.javafx.scene.layout.MigPane;

import java.util.ArrayList;
import java.util.List;

public class OptionalViewBar {
  private MigPane buttonBar = new MigPane(LayoutUtils.fillWithoutInsets());
  private List<String> registeredIds = new ArrayList<>();

  public void setView(String title, OptionalView view) {
    boolean isNewId = !registeredIds.contains(title);
    if (isNewId) {
      addButtonForTitle(title, view);
      registeredIds.add(title);
    }
  }

  private void addButtonForTitle(String title, final OptionalView view) {
    Button button = new Button();
    button.setText(title);
    button.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent actionEvent) {
        view.toggle();
      }
    });
    button.setRotate(90);
    button.translateYProperty().bind(button.widthProperty().divide(2));
    button.translateXProperty().bind(button.heightProperty());
    buttonBar.add(button, new CC().alignY("top"));
  }

  public Node getNode() {
    return buttonBar;
  }
}
