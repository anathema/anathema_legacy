package net.sf.anathema.swing.character.perspective;

import net.sf.anathema.framework.perspective.PerspectiveToolBar;
import net.sf.anathema.interaction.Interaction;
import net.sf.anathema.interaction.ToggleInteraction;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.swing.character.perspective.interaction.ActionInteraction;
import net.sf.anathema.swing.character.perspective.interaction.ToggleActionInteraction;

import javax.swing.JComponent;

public class InteractionView implements IView {

  private final PerspectiveToolBar toolbar = new PerspectiveToolBar();
  private final ActionInteraction newInteraction;
  private final ActionInteraction saveInteraction;
  private final ActionInteraction quickPrintInteraction;
  private final ActionInteraction controlledPrintInteraction;
  private final ToggleActionInteraction experiencedInteraction;

  public InteractionView(Resources resources) {
    this.newInteraction = new ActionInteraction(resources, this.getClass());
    this.saveInteraction = new ActionInteraction(resources, this.getClass());
    this.quickPrintInteraction = new ActionInteraction(resources, this.getClass());
    this.controlledPrintInteraction = new ActionInteraction(resources, this.getClass());
    this.experiencedInteraction = new ToggleActionInteraction(resources, this.getClass());
    newInteraction.addTo(toolbar);
    saveInteraction.addTo(toolbar);
    quickPrintInteraction.addTo(toolbar);
    controlledPrintInteraction.addTo(toolbar);
    experiencedInteraction.addTo(toolbar);
  }

  public Interaction getNewInteraction() {
    return newInteraction;
  }

  public Interaction getSaveInteraction() {
    return saveInteraction;
  }

  public Interaction getControlledPrintInteraction() {
    return controlledPrintInteraction;
  }

  public ToggleInteraction getExperiencedInteraction() {
    return experiencedInteraction;
  }

  @Override
  public JComponent getComponent() {
    return toolbar.getComponent();
  }

  public ActionInteraction getQuickPrintInteraction() {
    return quickPrintInteraction;
  }
}
