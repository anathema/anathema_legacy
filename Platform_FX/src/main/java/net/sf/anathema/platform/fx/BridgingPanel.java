package net.sf.anathema.platform.fx;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;

import javax.swing.JComponent;

public class BridgingPanel {
  private final JFXPanel panel = new JFXPanel();

  public void init(NodeHolder view) {
    init(new ViewHolder(view));
  }

  public void init(ParentHolder view) {
    Platform.runLater(new InitScene(panel, view));
  }

  public JComponent getComponent() {
    return panel;
  }
}