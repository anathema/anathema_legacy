package net.sf.anathema.swing.character.perspective;

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

  public InteractionView(IResources resources) {
    this.saveInteraction = new ActionInteraction(resources, this.getClass());
    this.experiencedInteraction = new ToggleActionInteraction(resources, this.getClass());
    toolbar.add(saveInteraction.getButton());
    toolbar.add(experiencedInteraction.getToggleButton());
    toolbar.setFloatable(false);
  }

  public Interaction getSaveInteraction() {
    return saveInteraction;
  }

  public ToggleInteraction getExperiencedInteraction() {
    return experiencedInteraction;
  }

  @Override
  public JComponent getComponent() {
    return toolbar;
  }
}
