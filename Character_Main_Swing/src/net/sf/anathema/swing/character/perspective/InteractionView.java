package net.sf.anathema.swing.character.perspective;

import net.sf.anathema.framework.reporting.AbstractPrintAction;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.swing.character.perspective.interaction.ActionInteraction;
import net.sf.anathema.swing.character.perspective.interaction.Interaction;
import net.sf.anathema.swing.character.perspective.interaction.ToggleActionInteraction;
import net.sf.anathema.swing.character.perspective.interaction.ToggleInteraction;

import javax.swing.JComponent;
import javax.swing.JToolBar;

public class InteractionView implements IView {

  private final JToolBar toolbar = new JToolBar();
  private final ActionInteraction saveInteraction;
  private final ToggleActionInteraction experiencedInteraction;
  private final ActionInteraction controlledPrintInteraction;
  private final ActionInteraction quickPrintInteraction;

  public InteractionView(IResources resources) {
    toolbar.setFloatable(false);
    this.saveInteraction = new ActionInteraction(resources, this.getClass());
    this.experiencedInteraction = new ToggleActionInteraction(resources, this.getClass());
    this.controlledPrintInteraction = new ActionInteraction(resources, this.getClass());
    this.quickPrintInteraction = new ActionInteraction(resources, this.getClass());
    toolbar.add(saveInteraction.getButton());
    toolbar.add(experiencedInteraction.getToggleButton());
    toolbar.addSeparator();
    toolbar.add(quickPrintInteraction.getButton());
    toolbar.add(controlledPrintInteraction.getButton());
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
