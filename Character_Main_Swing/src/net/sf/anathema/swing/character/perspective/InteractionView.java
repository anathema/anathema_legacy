package net.sf.anathema.swing.character.perspective;

import net.sf.anathema.interaction.Interaction;
import net.sf.anathema.interaction.ToggleInteraction;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.swing.character.perspective.interaction.ActionInteraction;
import net.sf.anathema.swing.character.perspective.interaction.ToggleActionInteraction;

import javax.swing.JComponent;
import javax.swing.JToolBar;

public class InteractionView implements IView {

  private final JToolBar toolbar = new JToolBar();
  private final ActionInteraction saveInteraction;
  private final ToggleActionInteraction experiencedInteraction;
  private final ActionInteraction controlledPrintInteraction;
  private final ActionInteraction quickPrintInteraction;
  private final ActionInteraction newInteraction;

  public InteractionView(IResources resources) {
    toolbar.setFloatable(false);
    this.newInteraction = new ActionInteraction(resources, this.getClass());
    this.saveInteraction = new ActionInteraction(resources, this.getClass());
    this.controlledPrintInteraction = new ActionInteraction(resources, this.getClass());
    this.quickPrintInteraction = new ActionInteraction(resources, this.getClass());
    this.experiencedInteraction = new ToggleActionInteraction(resources, this.getClass());
    toolbar.add(newInteraction.getButton());
    toolbar.add(saveInteraction.getButton());
    toolbar.addSeparator();
    toolbar.add(quickPrintInteraction.getButton());
    toolbar.add(controlledPrintInteraction.getButton());
    toolbar.addSeparator();
    toolbar.add(experiencedInteraction.getToggleButton());
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
    return toolbar;
  }

  public ActionInteraction getQuickPrintInteraction() {
    return quickPrintInteraction;
  }
}
