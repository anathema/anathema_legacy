package net.sf.anathema.platform.fx;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;

import javax.swing.JComponent;

public class BridgingPanel {
  private final JFXPanel panel = new JFXPanel();

  public void init(NodeHolder view, String... skinCss) {
    init(new ViewHolder(view), skinCss);
  }

  public void init(ParentHolder view, String... skinCss) {
    Platform.runLater(new InitScene(panel, view, skinCss));
  }

  public JComponent getComponent() {
    return panel;
  }
}