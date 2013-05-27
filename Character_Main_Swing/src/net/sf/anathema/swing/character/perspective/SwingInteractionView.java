package net.sf.anathema.swing.character.perspective;

import net.sf.anathema.framework.perspective.PerspectiveToolBar;
import net.sf.anathema.interaction.ToggleTool;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.platform.fx.InteractionView;
import net.sf.anathema.swing.character.perspective.interaction.ActionInteraction;
import net.sf.anathema.swing.character.perspective.interaction.ToggleActionInteraction;

import javax.swing.JComponent;

public class SwingInteractionView implements InteractionView {

  private final PerspectiveToolBar toolbar = new PerspectiveToolBar();
  private Resources resources;

  public SwingInteractionView(Resources resources) {
    this.resources = resources;
  }

  public JComponent getComponent() {
    return toolbar.getComponent();
  }

  @Override
  public Tool addTool() {
    ActionInteraction tool = new ActionInteraction(resources);
    tool.addTo(toolbar);
    SwingAcceleratorMap acceleratorMap = new SwingAcceleratorMap(getComponent().getActionMap(),
            getComponent().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW));
    tool.registerHotkeyIn(acceleratorMap);
    return tool;
  }

  @Override
  public ToggleTool addToggleTool() {
    ToggleActionInteraction tool = new ToggleActionInteraction(resources);
    tool.addTo(toolbar);
    return tool;
  }
}