package net.sf.anathema.platform.fx;

import javafx.scene.Node;
import javafx.scene.control.TitledPane;

public class StyledTitledPane {

  public static TitledPane Create(String titleString, Node content) {
    TitledPane titledPane = new TitledPane(titleString, content);
    titledPane.setCollapsible(false);
    titledPane.getStyleClass().add("titledsection");
    new Stylesheet("skin/platform/styledtitledpane.css").applyToParent(titledPane);
    return titledPane;
  }
}