package net.sf.anathema.fx.hero.creation;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import net.sf.anathema.hero.creation.ToggleButtonPanel;
import net.sf.anathema.interaction.ToggleTool;
import net.sf.anathema.platform.tool.FxToggleTool;
import org.tbee.javafx.scene.layout.MigPane;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.withoutInsets;

public class FxToggleButtonPanel implements ToggleButtonPanel {

  private final MigPane panel = new MigPane(withoutInsets().wrapAfter(2));
  private final ToggleGroup group = new ToggleGroup();


  public Node getNode() {
    return panel;
  }

  @Override
  public ToggleTool addButton(String label) {
    FxToggleTool tool = FxToggleTool.create();
    Node button = tool.getNode();
    tool.registerWithGroup(group);
    panel.add(button);
    panel.add(new Label(label));
    return tool;
  }
}
