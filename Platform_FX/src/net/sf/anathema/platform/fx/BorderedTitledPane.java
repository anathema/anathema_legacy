package net.sf.anathema.platform.fx;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

//John Smith's BorderedTitledPane, taken from http://stackoverflow.com/questions/14860960/groupbox-titledborder-in-javafx-2
public class BorderedTitledPane extends StackPane {

  public static BorderedTitledPane Create(String titleString, Node content) {
    BorderedTitledPane titledPane = new BorderedTitledPane(titleString, content);
    new Stylesheet("skin/platform/borderedtitledpane.css").applyToParent(titledPane);
    return titledPane;
  }

  private BorderedTitledPane(String titleString, Node content) {
    Label title = new Label(" " + titleString + " ");
    title.getStyleClass().add("bordered-titled-title");
    StackPane.setAlignment(title, Pos.TOP_CENTER);

    StackPane contentPane = new StackPane();
    content.getStyleClass().add("bordered-titled-content");
    contentPane.getChildren().add(content);

    getStyleClass().add("bordered-titled-border");
    getChildren().addAll(title, contentPane);
  }
}