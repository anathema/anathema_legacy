package net.sf.anathema.character.library.trait.view.fx;

import net.miginfocom.layout.CC;
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
  private FxGroupedColumnPanel parent;

  public FxExtensibleTraitView(FxTraitView view) {
    this.view = view;
    view.addTo(center);
  }

  @Override
  public IIntValueView getIntValueView() {
    return view;
  }

  @Override
  public ToggleTool addToggleInFront() {
    FxToggleTool toggleTool = FxToggleTool.create();
    addToPanel(front, toggleTool);
    return toggleTool;
  }

  @Override
  public ToggleTool addToggleBehind(IIconToggleButtonProperties properties) {
    FxToggleTool toggleTool = FxToggleTool.create();
    addToPanel(rear, toggleTool);
    return toggleTool;
  }

  @Override
  public Tool addToolBehind() {
    FxButtonTool buttonTool = FxButtonTool.ForToolbar();
    addToPanel(rear, buttonTool);
    return buttonTool;
  }

  @Override
  public void remove() {
    removePart(front);
    removePart(center);
    removePart(rear);
  }

  @SuppressWarnings("UnusedParameters")
  private void removePart(MigPane panel) {
    parent.remove(panel);
  }

  public void addTo(FxGroupedColumnPanel panel) {
    this.parent = panel;
    panel.add(front);
    panel.add(center, new CC().growX().pushX());
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