package net.sf.anathema.swing.character.perspective;

import net.sf.anathema.framework.perspective.PerspectiveToolBar;
import net.sf.anathema.interaction.ToggleTool;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.platform.fx.InteractionView;
import net.sf.anathema.swing.interaction.ActionInteraction;
import net.sf.anathema.swing.interaction.ToggleActionInteraction;

import javax.swing.JComponent;

import static javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW;

public class SwingInteractionView implements InteractionView {

  private final PerspectiveToolBar toolbar = new PerspectiveToolBar();

  public JComponent getComponent() {
    return toolbar.getComponent();
  }

  @Override
  public Tool addTool() {
    ActionInteraction tool = new ActionInteraction();
    tool.addTo(new AddToToolbar(toolbar));
    JComponent component = getComponent();
    SwingAcceleratorMap acceleratorMap = new SwingAcceleratorMap(component.getActionMap(),
            component.getInputMap(WHEN_IN_FOCUSED_WINDOW));
    tool.registerHotkeyIn(acceleratorMap);
    return tool;
  }

  @Override
  public ToggleTool addToggleTool() {
    ToggleActionInteraction tool = new ToggleActionInteraction();
    toolbar.add(tool.getButton());
    return tool;
  }
}