package net.sf.anathema.character.library.trait.view.fx;

import net.sf.anathema.character.library.intvalue.IIconToggleButtonProperties;
import net.sf.anathema.character.presenter.ExtensibleTraitView;
import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.interaction.ToggleTool;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.platform.fx.FxComponent;
import net.sf.anathema.platform.fx.FxThreading;
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
    view.addTo(center);
  }

  @Override
  public IIntValueView getIntValueView() {
    return view;
  }

  @Override
  public ToggleTool addToggleInFront(IIconToggleButtonProperties properties) {
    FxToggleTool toggleTool = new FxToggleTool();
    addToPanel(front, toggleTool);
    return toggleTool;
  }

  @Override
  public ToggleTool addToggleBehind(IIconToggleButtonProperties properties) {
    FxToggleTool toggleTool = new FxToggleTool();
    addToPanel(rear, toggleTool);
    return toggleTool;
  }

  @Override
  public Tool addToolBehind() {
    FxButtonTool buttonTool = FxButtonTool.ForAnyPurpose();
    addToPanel(rear, buttonTool);
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

  public void addTo(FxGroupedColumnPanel panel) {
    panel.add(front);
    panel.add(center);
    panel.add(rear);
  }

  private void addToPanel(final MigPane pane, final FxComponent toggleTool) {
    FxThreading.runOnCorrectThread(new Runnable() {
      @Override
      public void run() {
        pane.add(toggleTool.getNode());
      }
    });
  }
}