package net.sf.anathema.platform.fx;

import javafx.embed.swing.JFXPanel;

import javax.swing.JComponent;

public class BridgingPanel {
  private final JFXPanel panel = new JFXPanel();

  public void init(NodeHolder view) {
    init(new ViewHolder(view));
  }

  public void init(ParentHolder view) {
    FxThreading.runOnCorrectThread(new InitScene(panel, view));
  }

  public JComponent getComponent() {
    return panel;
  }
}