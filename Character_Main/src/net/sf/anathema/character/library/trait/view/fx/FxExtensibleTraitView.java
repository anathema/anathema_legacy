package net.sf.anathema.character.library.trait.view.fx;

import javafx.scene.Node;
import net.sf.anathema.character.library.intvalue.IIconToggleButtonProperties;
import net.sf.anathema.character.presenter.ExtensibleTraitView;
import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.interaction.ToggleTool;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.platform.tool.FxButtonTool;
import net.sf.anathema.platform.tool.FxToggleTool;
import org.tbee.javafx.scene.layout.MigPane;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.fillWithoutInsets;

public class FxExtensibleTraitView implements ExtensibleTraitView {
  private final MigPane front = new MigPane(fillWithoutInsets());
  private final MigPane center = new MigPane(fillWithoutInsets().wrapAfter(2));
  private final MigPane rear = new MigPane(fillWithoutInsets());
  private FxTraitView view;

  public FxExtensibleTraitView(FxTraitView view) {
    this.view = view;
  }

  public Node getNode() {
    return view.getNode();
  }

  @Override
  public IIntValueView getIntValueView() {
    return view;
  }

  @Override
  public ToggleTool addToggleInFront(IIconToggleButtonProperties properties) {
    FxToggleTool toggleTool = new FxToggleTool();
    front.add(toggleTool.getNode());
    return toggleTool;
  }

  @Override
  public ToggleTool addToggleBehind(IIconToggleButtonProperties properties) {
    FxToggleTool toggleTool = new FxToggleTool();
    rear.add(toggleTool.getNode());
    return toggleTool;
  }

  @Override
  public Tool addToolBehind() {
    FxButtonTool buttonTool = FxButtonTool.ForAnyPurpose();
    rear.add(buttonTool.getNode());
    return buttonTool;
  }

  @Override
  public void remove() {
    removePart(front);
    removePart(center);
    removePart(rear);
  }

  private void removePart(MigPane panel) {
    //panel.getParent().getChildren().remove(panel);
  }
}
