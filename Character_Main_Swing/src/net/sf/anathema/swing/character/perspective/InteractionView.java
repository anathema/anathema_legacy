package net.sf.anathema.swing.character.perspective;

import net.sf.anathema.framework.perspective.PerspectiveToolBar;
import net.sf.anathema.interaction.ToggleTool;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.swing.character.perspective.interaction.ActionInteraction;
import net.sf.anathema.swing.character.perspective.interaction.ToggleActionInteraction;

import javax.swing.JComponent;

public class InteractionView {

  private final PerspectiveToolBar toolbar = new PerspectiveToolBar();
  private final ActionInteraction newInteraction;
  private final ActionInteraction saveInteraction;
  private final ActionInteraction quickPrintInteraction;
  private final ActionInteraction controlledPrintInteraction;
  private final ToggleActionInteraction experiencedInteraction;

  public InteractionView(Resources resources) {
    this.newInteraction = new ActionInteraction(resources);
    this.saveInteraction = new ActionInteraction(resources);
    this.quickPrintInteraction = new ActionInteraction(resources);
    this.controlledPrintInteraction = new ActionInteraction(resources);
    this.experiencedInteraction = new ToggleActionInteraction(resources);
    newInteraction.addTo(toolbar);
    saveInteraction.addTo(toolbar);
    quickPrintInteraction.addTo(toolbar);
    controlledPrintInteraction.addTo(toolbar);
    experiencedInteraction.addTo(toolbar);
  }

  public Tool getNewInteraction() {
    return newInteraction;
  }

  public Tool getSaveInteraction() {
    return saveInteraction;
  }

  public Tool getControlledPrintInteraction() {
    return controlledPrintInteraction;
  }

  public ToggleTool getExperiencedInteraction() {
    return experiencedInteraction;
  }

  public JComponent getComponent() {
    return toolbar.getComponent();
  }

  public ActionInteraction getQuickPrintInteraction() {
    return quickPrintInteraction;
  }
}
