package net.sf.anathema.platform.fx;

import javafx.scene.Node;
import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import org.tbee.javafx.scene.layout.MigPane;

public class PerspectivePane {

  private MigPane outerPane = new MigPane(new LC().fill());

  public PerspectivePane(final String... styleSheetPaths) {
    for (String sheetPath : styleSheetPaths) {
      new Stylesheet(sheetPath).applyToParent(outerPane);
    }
  }

  public void setNavigationComponent(final Node component) {
    outerPane.add(component, new CC().grow().minWidth("200").width("200").maxWidth("200"));
  }

  public void setContentComponent(final Node component) {
    outerPane.add(component, new CC().grow().push());
  }


  public void addStyleSheetClass(String styleClass) {
    outerPane.getStyleClass().add(styleClass);
  }

  public Node getNode() {
    return outerPane;
  }
}